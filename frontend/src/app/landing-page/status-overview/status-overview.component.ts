import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-status-overview',
  templateUrl: './status-overview.component.html',
  styleUrls: ['./status-overview.component.css']
})
export class StatusOverviewComponent implements OnInit {

  holdings: any = [
    {symbol: "TSLA", name: "Tesla Inc", holdings:2, value: 1500},
    {symbol: "C", name: "Citigroup Inc", holdings:10, value: 720},
    {symbol: "NFLX", name: "Netflix Inc", holdings: 1, value: 550}
  ];

  displayedHoldingsColumns: any = ["symbol", "name", "holdings", "value"]

  recentTrades: any = [
    {symbol: "AAPL", price: 150, type: "Buy", status: "Processing"},
    {symbol: "TSLA", price: 650, type: "Buy", status: "Processing"},
    {symbol: "NFLX", price: 500, type: "Buy", status: "Filled"},
  ]

  displayedRecentTradesColumns: any = ["symbol", "price", "type", "status"]

  constructor() { }

  ngOnInit(): void {
  }

}
