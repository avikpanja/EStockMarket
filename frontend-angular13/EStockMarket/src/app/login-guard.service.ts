import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from './user-login/user.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuardService implements CanActivate {

  constructor(private router: Router, private us: UserService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean  {
    if(state.url=='/') {
      if(this.us.isUserLoggedIn()) {
        this.router.navigate(['/dashboard']);
        return false;
      } 
      return true;
    }

    if(this.us.isUserLoggedIn()) {
      console.log("user logged in");
      return true;
    }
    this.router.navigate(['/']);
    return false;
  }
}
