import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-top-gainers-losers',
  templateUrl: './top-gainers-losers.component.html',
  styleUrls: ['./top-gainers-losers.component.css']
})
export class TopGainersLosersComponent implements OnInit {

  topGainers: any;
  topLosers: any;

  constructor() {
    this.topGainers = [
      {symbol: "FB", name: "Facebook Inc", price:365.51, change:6.23},
      {symbol: "AAPL", name: "Apple Inc", price:149.62, change:3.65},
      {symbol: "AMZN", name: "Amazon", price:3305.78, change:3.142},
      {symbol: "NFLX", name: "Netflix", price:553.41, change:2.718},
      {symbol: "GOOGL", name: "Alphabet Inc", price:2825.23, change:1.414},
    ]

    this.topLosers = [
      {symbol: "RGC", name: "Regenell Bioscience", price:15.3, change:20.16 },
      {symbol: "MKTW", name: "Marketwise Inc", price:6.8, change:14.14},
      {symbol: "LABD", name: "Direxon Daily", price:21.17, change:8.43 },
      {symbol: "SINV", name: "ETFMG Silver Miners ETF", price:14.62, change:5.64},
      {symbol: "GDXD", name: "Microsectors Gold Miners", price:22.18, change:3.18 }
    ]
  }

  ngOnInit(): void {
  }

}
