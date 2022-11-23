import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CompanyComponent } from './company/company.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';

import { MatIconModule } from '@angular/material/icon';

import { MatListModule } from '@angular/material/list';

import { MatTableModule} from '@angular/material/table';

import { MatExpansionModule } from '@angular/material/expansion';

import { MatInputModule } from '@angular/material/input';

import { MatFormFieldModule } from '@angular/material/form-field';

import { MatCardModule } from '@angular/material/card';

import {MatSelectModule} from '@angular/material/select';
import {MatPaginatorModule} from '@angular/material/paginator';

import {MatDialogModule} from '@angular/material/dialog';
import {MatSortModule} from '@angular/material/sort';
import { StockComponent } from './stock/stock.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { FooterComponent } from './footer/footer.component';
import { DatePipe } from '@angular/common';
import { UserLoginComponent } from './user-login/user-login.component';
import { RequestInterceptor } from './request.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    StockComponent,
    NavigationBarComponent,
    CompanyRegistrationComponent,
    FooterComponent,
    UserLoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatGridListModule,
    MatCardModule,
    // MatDialogModule,
    // MatExpansionModule,
    // MatFormFieldModule,
    // MatListModule,
    // MatSelectModule,
    // MatSidenavModule,
    // MatToolbarModule,
    MatIconModule,
    MatTableModule, 
    MatInputModule,
    MatSortModule,
    MatPaginatorModule
  ],
  providers: [DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
