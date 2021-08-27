import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-holdings',
  templateUrl: './user-holdings.component.html',
  styleUrls: ['./user-holdings.component.css']
})
export class UserHoldingsComponent implements OnInit {

  // stocks = STOCKS;

  @Input()
  userAssets: any;
  
  
  constructor() { }

  ngOnInit(): void {
    
  }

}
