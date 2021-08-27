import { Component, Input, OnInit } from '@angular/core';

interface TradeHist{
  date: Date;
  symbol: string;
  name: string;
  type: string;
  profitOrLoss: number;
}

@Component({
  selector: 'app-trading-history',
  templateUrl: './trading-history.component.html',
  styleUrls: ['./trading-history.component.css']
})
export class TradingHistoryComponent implements OnInit {
  
  @Input()
  userHist: any;
  

  constructor() { }

  ngOnInit(): void {
  }

  

}