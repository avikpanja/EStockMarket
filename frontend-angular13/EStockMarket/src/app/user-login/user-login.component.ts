import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserModel } from '../model/user.model';
import { UserService } from './user.service';

@Component({
  selector: 'user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  regForm = new FormGroup({
    firstname : new FormControl('', [Validators.required, Validators.min(2)]),
    lastname : new FormControl('', []),
    username : new FormControl('', [Validators.required]),
    password : new FormControl('', [Validators.required])
  });

  loginForm = new FormGroup({
    username : new FormControl('', [Validators.required]),
    password : new FormControl('', [Validators.required])
  });

  enableLoginBlock = true;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  onLoginFormSubmit(loginFormDir: FormGroupDirective) {
    let username:string = this.loginForm.controls['username'].value;
    username = username.trim().toLowerCase();
    let password = this.loginForm.controls['password'].value;
    if(username && password) {
      this.userService.doLogin(username,password).subscribe(data=> {
        this.router.navigate(['/dashboard']);
      }, error => {
        alert("Worng username or password!");
        loginFormDir.resetForm();
      });
    }
    
  }

  onRegFormSubmit = (regFormDir: FormGroupDirective) => {
    let user = new UserModel(
      this.regForm.controls['firstname'].value,
      this.regForm.controls['username'].value,
      this.regForm.controls['password'].value,
      this.regForm.controls['lastname'].value,
    );
    this.userService.addNewUser(user).subscribe(
      data=> {
        regFormDir.resetForm();
        alert("Registration successful");
      }, error => {
        console.log(JSON.stringify(error));
        alert("Registration unsuccessful");
      }
    );
  }

}
