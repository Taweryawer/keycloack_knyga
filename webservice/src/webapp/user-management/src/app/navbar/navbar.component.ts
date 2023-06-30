import { Component, OnInit } from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {JwtHelperService} from "../helpers/jwt-helper.service";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private oauthService: OAuthService,
    private jwtService: JwtHelperService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  isLoggedIn(): boolean {
    return this.oauthService.hasValidAccessToken();
  }

  getUsername() {
    return this.jwtService.getUsername();
  }

  logout() {
    this.authService.logout();
  }

  redirectToUserProfile(): void {
    window.location.href = '/auth/realms/Knygers/account/';
  }
}
