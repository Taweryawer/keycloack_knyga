import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {OAuthService} from "angular-oauth2-oidc";
import {BehaviorSubject, combineLatest, Observable, ReplaySubject} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isAuthenticatedSubject$ = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject$.asObservable();

  private isDoneLoadingSubject$ = new ReplaySubject<boolean>();
  public isDoneLoading$ = this.isDoneLoadingSubject$.asObservable();

  public canActivateProtectedRoutes$: Observable<boolean> = combineLatest([
    this.isAuthenticated$,
    this.isDoneLoading$
  ]).pipe(map(values => values.every(b => b)));

  constructor(private router: Router,
              private oauthService: OAuthService) {
    window.addEventListener('storage', (event) => {
      if (event.key !== 'access_token' && event.key !== null) {
        return;
      }

      this.isAuthenticatedSubject$.next(this.oauthService.hasValidAccessToken());

      if (!this.oauthService.hasValidAccessToken()) {
        this.router.navigateByUrl('/login');
      }
    });

    this.oauthService.events
      .subscribe(() => {
        this.isAuthenticatedSubject$.next(this.oauthService.hasValidAccessToken());
      });
  }

  public initialLogin(): Promise<void> {
    return this.oauthService.loadDiscoveryDocumentAndTryLogin()
      .then(() => {
        if (this.oauthService.hasValidAccessToken()) {
          return Promise.resolve();
        }
        if (this.oauthService.getRefreshToken()) {
          return this.oauthService.refreshToken()
            .then(() => {
              Promise.resolve()
            })
            .catch(result => {
              const errorResponsesRequiringUserInteraction = [
                'interaction_required',
                'login_required',
                'account_selection_required',
                'consent_required',
                'invalid_grant'
              ];
              if (result
                && result.reason
                && errorResponsesRequiringUserInteraction.indexOf(result.reason.params.error) >= 0) {

                this.login()

                return Promise.resolve();
              }

              return Promise.reject(result);
            });
        } else {
          this.login();
          return Promise.resolve();
        }
      })
      .then(() => {
        this.isDoneLoadingSubject$.next(true);
      })
  }

  public login() {
    this.oauthService.initCodeFlow();
  }

  public logout() {
    this.oauthService.logOut();
  }
}
