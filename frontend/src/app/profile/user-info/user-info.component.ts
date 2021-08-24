import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  fullName: string = "Nicole Tan";
  amt: number = 10000;

  constructor() { }

  ngOnInit(): void {
  }

}
