import { Component, Input, OnInit } from '@angular/core';
import { Sort } from 'src/app/util/sort';

interface TradeStatus{
  symbol: string;
  name: string;
  type: string;
  status: string;
}

@Component({
  selector: 'app-trade-status',
  templateUrl: './trade-status.component.html',
  styleUrls: ['./trade-status.component.css']
})
export class TradeStatusComponent implements OnInit {
  
  @Input()
  userStatus:any;

  constructor() { }

  ngOnInit(): void {
    
  }

}
