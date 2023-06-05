import { Component, OnInit } from '@angular/core';
import {RegisterResponse} from "../register-user/register-user.component";
import {Router} from "@angular/router";
import {OAuthService} from "angular-oauth2-oidc";
import {AuthService} from "../service/auth.service";

export class LoginForm {
  constructor(
    public username:string,
    public password:string,
  ) {
  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error: boolean = false;
  form: LoginForm = new LoginForm("", "");

  constructor(private router: Router,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.login();
  }

}
