import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { RegisterUserComponent } from './register-user/register-user.component';
import { FormsModule } from "@angular/forms";
import {RecaptchaFormsModule, RecaptchaModule} from "ng-recaptcha";
import { NavbarComponent } from './navbar/navbar.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminAddUserComponent } from './admin-add-user/admin-add-user.component';
import { AdminEditUserComponent } from './admin-edit-user/admin-edit-user.component';
import { AdminRemoveUserComponent } from './admin-remove-user/admin-remove-user.component';
import {OAuthModule} from "angular-oauth2-oidc";
import {LoginComponent} from "./login/login.component";
import { FallbackComponent } from './fallback/fallback.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    RegisterUserComponent,
    NavbarComponent,
    LoginComponent,
    HomepageComponent,
    AdminAddUserComponent,
    AdminEditUserComponent,
    AdminRemoveUserComponent,
    FallbackComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RecaptchaModule,
    RecaptchaFormsModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['http://localhost:8081/api'],
        sendAccessToken: true
      }
    })
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
