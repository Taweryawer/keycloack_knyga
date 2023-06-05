import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router
} from '@angular/router';
import { Observable } from 'rxjs';
import jwtDecode from "jwt-decode";
import {JwtHelperService} from "../helpers/jwt-helper.service";

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private router: Router,
              private jwtHelper: JwtHelperService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let payload = jwtDecode(sessionStorage.getItem('access_token')!, { header: false });
    // @ts-ignore
    if (this.jwtHelper.isAdmin(payload)) {
      this.router.navigateByUrl("/admin");
      return false;
    } else {
      return true;
    }
  }

}
