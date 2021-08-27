import { Component, Input, OnInit } from '@angular/core';
import { Sort } from 'src/app/util/sort';

interface TradeStatus{
  symbol: string;
  name: string;
  type: string;
  status: string;
}

<<<<<<< HEAD
=======
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

>>>>>>> new_dn_backend
@Component({
  selector: 'app-trade-status',
  templateUrl: './trade-status.component.html',
  styleUrls: ['./trade-status.component.css']
})
export class TradeStatusComponent implements OnInit {
<<<<<<< HEAD
=======

  // tradeStatus = TRADESTATUS;
>>>>>>> new_dn_backend
  
  @Input()
  userStatus:any;

  constructor() { }

  ngOnInit(): void {
    
  }

}
