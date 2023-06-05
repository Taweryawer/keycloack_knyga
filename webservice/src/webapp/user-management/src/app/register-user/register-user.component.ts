import {Component, OnInit} from '@angular/core';
import {HttpClientService} from "../service/http-client.service";
import {Router} from "@angular/router";

export class RegisterForm {
  constructor(
    public login:string,
    public password:string,
    public passwordr:string,
    public email:string,
    public firstName:string,
    public lastName:string,
    public birthday:string,
    public recaptchaResponse:string
  ) {
  }
}

export class RegisterResponse {
  constructor(
    public success: boolean,
    public errors: Map<string, string>,
    public captchaError: boolean
  ) {

  }
}

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  form: RegisterForm = new RegisterForm("","","","","","","", "");
  response: RegisterResponse = new RegisterResponse(true, new Map<string, string>(), false);

  constructor(
    private httpClientService:HttpClientService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  registerUser(): void {
    this.httpClientService.registerUser(this.form).subscribe(
       response => {
         let success = response["success"];
         let errors = new Map<string, string>();
         if (response["errors"] != undefined) {
           errors = new Map<string, string>(Object.entries(response["errors"]));
         }
         this.response = new RegisterResponse(success, errors, response["captchaError"]);
         if (!success) {
           grecaptcha.reset();
         } else {
           this.router.navigateByUrl("/");
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
