import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, ObservableInput, throwError } from 'rxjs';
import { UserService } from './user-login/user.service';
import { Router } from '@angular/router';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  constructor(private userService: UserService, private router: Router) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("RequestInterceptor");
    const jwtToken = this.userService.jwtToken;
    
    request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${jwtToken}`
        }
    });
    return next.handle(request).pipe(
      catchError(this.handleError)
    );
  }

  private handleError = (error: HttpErrorResponse):ObservableInput<any>=> {
    console.log(JSON.stringify(error));
    this.userService.doLogout();
    location.reload();
    let err = error.error.message || error.statusText;
    return throwError(err);
  }

  // private handleError1(): ObservableInput<any>{
  //   console.log("request con "+ this);
  //   return "";
  // }
}
