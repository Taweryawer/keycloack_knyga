import { Component, OnInit } from '@angular/core';
import jwtDecode from "jwt-decode";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {


  constructor() { }

  ngOnInit(): void {
  }

  getName(): string {
    let payload = jwtDecode(sessionStorage.getItem('access_token')!, { header: false });
    // @ts-ignore
    return payload.name;
  }
}
