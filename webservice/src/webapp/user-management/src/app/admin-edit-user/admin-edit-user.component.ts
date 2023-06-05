import { Component, OnInit } from '@angular/core';
import {HttpClientService, Role} from "../service/http-client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {
  AdminUserForm,
  AdminUserResponse
} from "../admin-add-user/admin-add-user.component";

@Component({
  selector: 'app-admin-edit-user',
  templateUrl: './admin-edit-user.component.html',
  styleUrls: ['./admin-edit-user.component.css']
})
export class AdminEditUserComponent implements OnInit {

  constructor(
    private httpService: HttpClientService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  roles: Role[] = [];
  form: AdminUserForm = new AdminUserForm("","","","","","","", "");
  response: AdminUserResponse = new AdminUserResponse(true, new Map<string, string>());

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.form.login = params.username
      });
    this.httpService.getUser(this.form.login).subscribe(
        response => {
          this.form.email = response.email;
          this.form.firstName = response.firstName;
          this.form.lastName = response.lastName;
          this.form.birthday = response.birthday;
          this.form.roleId = response.roleId;
        }
    )
    this.httpService.getAllRoles().subscribe(
      response => {
        this.roles = response
      }
    )
  }

  editUser(): void {
    this.httpService.editUser(this.form).subscribe(
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
