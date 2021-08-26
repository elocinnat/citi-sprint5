import { Component, Input, OnInit } from '@angular/core';

interface TradeStatus{
  symbol: string;
  name: string;
  type: string;
  status: string;
}

// const TRADESTATUS: TradeStatus[] = [
//   {
//     symbol: 'AAPL',
//     name: 'Apple Inc.',
//     type: 'Buy',
//     status: 'Rejected'
//   },
//   {
//     symbol: 'AAPL',
//     name: 'Apple Inc.',
//     type: 'Buy',
//     status: 'Rejected'
//   },
//   {
//     symbol: 'AAPL',
//     name: 'Apple Inc.',
//     type: 'Buy',
//     status: 'Filled'
//   },
//   {
//     symbol: 'AAPL',
//     name: 'Apple Inc.',
//     type: 'Buy',
//     status: 'Processing'
//   },
// ] 

@Component({
  selector: 'app-trade-status',
  templateUrl: './trade-status.component.html',
  styleUrls: ['./trade-status.component.css']
})
export class TradeStatusComponent implements OnInit {

  // tradeStatus = TRADESTATUS;
  
  @Input()
  userStatus:any;

  constructor() { }

  ngOnInit(): void {
  }

}
