import { Component, OnInit } from '@angular/core';
import { UserContext } from './model/user-context.model';
import { UserService } from './user-login/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'EStockMarket';
  user : UserContext | any;

  constructor(private userService: UserService) {
    this.userService.currentUser.subscribe(user=>this.user=user);
  }
  
  
}
