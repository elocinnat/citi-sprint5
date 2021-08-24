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
      {symbol: "FB", name: "Facebook Inc"},
      {symbol: "AAPL", name: "Apple Inc"},
      {symbol: "AMZN", name: "Amazon"},
      {symbol: "NFLX", name: "Netflix"},
      {symbol: "GOOGL", name: "Alphabet Inc"},
    ]

    this.topLosers = [
      {symbol: "RGC", name: "Regenell Bioscience"},
      {symbol: "MKTW", name: "Marketwise Inc"},
      {symbol: "LABD", name: "Direxon Daily"},
      {symbol: "SINV", name: "ETFMG Silver Miners ETF"},
      {symbol: "GDXD", name: "Microsectors Gold Miners"}
    ]
  }

  ngOnInit(): void {
  }

}
