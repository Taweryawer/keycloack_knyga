import { Component, OnInit } from '@angular/core';
import {HttpClientService, Role} from "../service/http-client.service";
import {Router} from "@angular/router";

export class AdminUserForm {
  constructor(
    public login:string,
    public password:string,
    public passwordr:string,
    public email:string,
    public firstName:string,
    public lastName:string,
    public birthday:string,
    public roleId:string
  ) {
  }
}

export class AdminUserResponse {
  constructor(
    public success: boolean,
    public errors: Map<string, string>
  ) {

  }
}

@Component({
  selector: 'app-admin-add-user',
  templateUrl: './admin-add-user.component.html',
  styleUrls: ['./admin-add-user.component.css']
})
export class AdminAddUserComponent implements OnInit {

  constructor(
    private httpService: HttpClientService,
    private router: Router
  ) { }

  roles: Role[] = [];
  form: AdminUserForm = new AdminUserForm("","","","","","","", "");
  response: AdminUserResponse = new AdminUserResponse(true, new Map<string, string>());

  ngOnInit(): void {
    this.httpService.getAllRoles().subscribe(
      response => {
        this.roles = response
      }
    )
  }

  addUser(): void {
    this.httpService.addUser(this.form).subscribe(
      response => {
        let success = response["success"];
        let errors = new Map<string, string>();
        if (response["errors"] != undefined) {
          errors = new Map<string, string>(Object.entries(response["errors"]));
        }
        this.response = new AdminUserResponse(success, errors);
        if (success) {
          this.router.navigate(["../"]);
        }
      }
    )
  }

  getError(key: string): string | undefined {
    if (this.response.errors != undefined) {
      return this.response.errors.get(key);
    } else return undefined;
  }
}
