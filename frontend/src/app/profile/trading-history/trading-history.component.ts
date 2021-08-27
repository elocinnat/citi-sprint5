import { Component, Input, OnInit } from '@angular/core';

interface TradeHist{
  date: Date;
  symbol: string;
  name: string;
  type: string;
  profitOrLoss: number;
}

<<<<<<< HEAD
=======
// const TRADEHIST: TradeHist[] = [
//   {
//     date: new Date(),
//     symbol: 'TSLA',
//     name: 'Tesla, Inc.',
//     type: 'Sell',
//     profitOrLoss: 200
//   },
//   {
//     date: new Date(),
//     symbol: 'TSLA',
//     name: 'Tesla, Inc.',
//     type: 'Sell',
//     profitOrLoss: 200
//   },
//   {
//     date: new Date(),
//     symbol: 'TSLA',
//     name: 'Tesla, Inc.',
//     type: 'Sell',
//     profitOrLoss: 200
//   },
// ]


>>>>>>> new_dn_backend
@Component({
  selector: 'app-trading-history',
  templateUrl: './trading-history.component.html',
  styleUrls: ['./trading-history.component.css']
})
export class TradingHistoryComponent implements OnInit {
<<<<<<< HEAD
=======

  // tradeHist = TRADEHIST;
  
>>>>>>> new_dn_backend
  
  @Input()
  userHist: any;
  

  constructor() { }

  ngOnInit(): void {
<<<<<<< HEAD

=======
    // setTimeout(() => {
    //   // let headers: Object.keys(this.userHist[0])
    //   //if futhermore we can show the "arrow"
    //   const header = Object.keys(this.userHist[0]).find(x => x.sortable == 'created');
    //   header.direction = 'asc';

    //   //simply call the function onSort
    //   this.onSort({ column: 'name', direction: 'asc' });
    // });
>>>>>>> new_dn_backend
  }

  

}