import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { UserModel } from '../model/user.model';
import { CommonConstants } from '../common-constants';
import { UserContext } from '../model/user-context.model';

const REGISTER_NEW_USER_URL =  CommonConstants.LOCAL_HOST + 'auth/v1/user/addUser';
const USER_LOGIN_URL =  CommonConstants.LOCAL_HOST + 'auth/v1/user/login';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUserSubject: BehaviorSubject<UserContext>;
  public currentUser: Observable<UserContext>;

  constructor(private http:HttpClient) { 
    this.currentUserSubject = new BehaviorSubject<UserContext>(JSON.parse(<string>localStorage.getItem(CommonConstants.USER_CONTEXT))); 
    this.currentUser = this.currentUserSubject.asObservable();
  }

  addNewUser(user: UserModel):Observable<any> {
    return this.http.post<UserModel>(REGISTER_NEW_USER_URL, user);
  }

  doLogin(username: string, password: string): Observable<any>{
    let body = {
      "username": username,
      "password": password
    };
    return this.http.post<UserContext>(USER_LOGIN_URL,body).pipe(map(user=>{
      localStorage.setItem(CommonConstants.USER_CONTEXT, JSON.stringify(user));
      this.currentUserSubject.next(user);
      return user;
    }));
  }

  doLogout() {
    console.log("doLogout");
    localStorage.removeItem(CommonConstants.USER_CONTEXT);
    this.currentUserSubject.next(<UserContext>{});
  }

  isUserLoggedIn(): boolean {
    return localStorage.getItem(CommonConstants.USER_CONTEXT)!=null;
  }

  get currentUserContext(): UserContext|any {
    let userContext =  localStorage.getItem(CommonConstants.USER_CONTEXT);
    if(userContext!=null)
      return JSON.parse(userContext);
    return null;
  }

  get jwtToken(): string|any {
    let userContext =  localStorage.getItem(CommonConstants.USER_CONTEXT);
    if(userContext!=null)
      return JSON.parse(userContext).token;
    return null;
  }
}
