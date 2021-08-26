import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  fullName: string = "Nicole Tan";
  
  @Input()
  currency: any;

  constructor() { }

  ngOnInit(): void {
    console.log(this.currency)
  }

}
