import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {HttpClientService} from "../service/http-client.service";
import {AdminUserResponse} from "../admin-add-user/admin-add-user.component";

export class RemoveUserResponse {
  constructor(
    public success: boolean
  ) {
  }
}

@Component({
  selector: 'app-admin-remove-user',
  templateUrl: './admin-remove-user.component.html',
  styleUrls: ['./admin-remove-user.component.css']
})
export class AdminRemoveUserComponent implements OnInit {

  @Output()
  removed = new EventEmitter<string>();

  @Input('clickedUser')
  clickedUser: string = "";

  constructor(
    private httpService: HttpClientService
  ) { }

  ngOnInit(): void {
  }

  removeUser() {
    this.httpService.removeUser(this.clickedUser).subscribe(
      response => {
        let success = response["success"];
        if (success) {
          this.removed.emit('success');
        }
      }
    )
  }

}
