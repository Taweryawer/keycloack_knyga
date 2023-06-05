import { Injectable } from '@angular/core';
import jwtDecode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class JwtHelperService {

  constructor() { }

  public isAdmin(payload: any): boolean {
    let roles = payload.resource_access['spring-rest-backend'].roles;
    return roles.includes('Admin');
  }

  public getUsername(): boolean {
    let payload = this.getTokenPayloadFromSessionStorage();
    return payload.preferred_username;
  }

  getTokenPayloadFromSessionStorage(): any {
    return jwtDecode(sessionStorage.getItem('access_token')!, { header: false });
  }
}
