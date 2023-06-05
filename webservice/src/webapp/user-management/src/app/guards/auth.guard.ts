import {Injectable} from '@angular/core';
import {
  CanActivateChild,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router
} from '@angular/router';
import {Observable} from 'rxjs';
import {OAuthService} from "angular-oauth2-oidc";
import {AuthService} from "../service/auth.service";
import {filter, switchMap, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivateChild {

  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.authService.isDoneLoading$.pipe(
      filter(isDone => isDone),
      switchMap(_ => this.authService.isAuthenticated$),
      tap(isAuthenticated => isAuthenticated || this.authService.login())
    )
  }

}
