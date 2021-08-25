import { Component, Input, OnInit } from '@angular/core';

interface UserHoldings{
  symbol: string;
  name: string;
  qty: number;
  stockPrice: number;
  totalValuation: number;
}

// const STOCKS: UserHoldings[] = [
//   {
//     symbol: 'TSLA',
//     name: 'Tesla, Inc.',
//     qty: 2,
//     stockPrice: 223,
//     totalValuation: 1500
//   },
//   {
//     symbol: 'C',
//     name: 'Citigroup Inc.',
//     qty: 10,
//     stockPrice: 223,
//     totalValuation: 720
//   },
//   {
//     symbol: 'NFLX',
//     name: 'Netflix, Inc.',
//     qty: 1,
//     stockPrice: 223,
//     totalValuation: 550
//   },
// ]

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
