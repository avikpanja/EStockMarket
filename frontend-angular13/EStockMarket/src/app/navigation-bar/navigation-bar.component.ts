import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonConstants } from '../common-constants';
import { UserContext } from '../model/user-context.model';
import { UserService } from '../user-login/user.service';

@Component({
  selector: 'navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  companyName = "EStockApp"
  userContext: UserContext | any;
  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.userContext = this.userService.currentUserContext;
  }

  logOut() {
    console.log("logginOut");
    this.userService.doLogout();
    this.router.navigate(['/']);
  }

}
