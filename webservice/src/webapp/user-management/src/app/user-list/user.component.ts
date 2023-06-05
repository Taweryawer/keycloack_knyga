import {
  Component,
  ElementRef,
  Injectable,
  OnInit,
  ViewChild
} from '@angular/core';
import {HttpClientService, User} from "../service/http-client.service";
import jwtDecode from "jwt-decode";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserListComponent implements OnInit {

  @ViewChild('removeModal') modal: ElementRef | undefined;
  users: User[] | undefined;
  clickedUser: string = "";

  constructor(
    private httpClientService:HttpClientService
  ) { }

  ngOnInit(): void {
    this.httpClientService.getAllUsers().subscribe(
      response => this.handleResponse(response),
    );
  }

  handleResponse(response: User[]) {
    this.users = response;
  }

  getAgeForBirthday(birthday: string) {
    let date = new Date(birthday);
    return new Date().getFullYear() - date.getFullYear();
  }

  openRemoveModal(user: string) {
    this.clickedUser = user;
  }

  updateUserList(event: string) {
    if (event === 'success') {
      this.httpClientService.getAllUsers().subscribe(
        response => this.handleResponse(response),
      );
    }
  }

}
