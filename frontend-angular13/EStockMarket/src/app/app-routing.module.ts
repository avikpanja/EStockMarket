import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { CompanyComponent } from './company/company.component';
import { LoginGuardService } from './login-guard.service';
import { StockComponent } from './stock/stock.component';
import { UserLoginComponent } from './user-login/user-login.component';

const routes: Routes = [
  {path: '', component: UserLoginComponent, canActivate:[LoginGuardService]},
  {path: 'dashboard', component: CompanyComponent, canActivate:[LoginGuardService]},
  {path: 'stock/:companyCode', component: StockComponent, canActivate:[LoginGuardService]},
  {path: 'register-company', component: CompanyRegistrationComponent, canActivate:[LoginGuardService]},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
