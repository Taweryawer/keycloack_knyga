import { Component } from '@angular/core';
import {
  AuthConfig, JwksValidationHandler,
  NullValidationHandler,
  OAuthService
} from "angular-oauth2-oidc";
import {environment} from "../environments/environment";
import {AuthService} from "./service/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'user-management';

  constructor(private oauthService: OAuthService, private authService: AuthService) {
    this.configure();
    this.authService.initialLogin();
  }

  authConfig: AuthConfig = {
    issuer: environment.keycloakAuth,
    redirectUri: window.location.origin + '/',
    clientId: "angular-app",
    scope: "openid profile email",
    responseType: 'code',
    disableAtHashCheck: true,
    showDebugInformation: true,
    requireHttps: false,
    skipIssuerCheck: true
  }


  private configure() {
    this.oauthService.configure(this.authConfig);
    this.oauthService.tokenValidationHandler = new NullValidationHandler();
    this.oauthService.setupAutomaticSilentRefresh();
  }
}
