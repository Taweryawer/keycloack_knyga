import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {
  RegisterForm,
  RegisterResponse
} from "../register-user/register-user.component";
import {
  AdminUserForm,
  AdminUserResponse
} from "../admin-add-user/admin-add-user.component";
import {RemoveUserResponse} from "../admin-remove-user/admin-remove-user.component";
import {environment} from "../../environments/environment";

export class User {
  constructor(
    public login:string,
    public email:string,
    public firstName:string,
    public lastName:string,
    public birthday:string,
    public roleId:string
  ) {
  }
}

export class Role {
  constructor(
    public id:string,
    public name:string
  ) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  apiGetAll = environment.apiGetAllUsers;
  apiRoles = environment.apiRoles;
  apiRegister = environment.apiRegister;
  apiAdd = environment.apiAddUser;
  apiGet = environment.apiGetUser;
  apiEdit = environment.apiEditUser;
  apiRemove = environment.apiRemoveUser;

  constructor(
    private httpClient:HttpClient
  ) { }

  getAllUsers() {
    return this.httpClient.get<User[]>(this.apiGetAll);
  }

  getAllRoles() {
    return this.httpClient.get<Role[]>(this.apiRoles)
  }

  registerUser(user: RegisterForm) {
    return this.httpClient.post<RegisterResponse>(this.apiRegister, user)
  }

  addUser(user: AdminUserForm) {
    return this.httpClient.post<AdminUserResponse>(this.apiAdd, user);
  }

  getUser(user: string) {
    return this.httpClient.get<User>(this.apiGet,{params: {
        username: user
      }})
  }

  editUser(user: AdminUserForm) {
    return this.httpClient.post<AdminUserResponse>(this.apiEdit, user);
  }

  removeUser(user: string) {
    return this.httpClient.post<RemoveUserResponse>(this.apiRemove, {username: user})
  }
}
