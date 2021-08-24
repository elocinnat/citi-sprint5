import { Component, OnInit } from '@angular/core';

interface TradeHist{
  date: Date;
  symbol: string;
  name: string;
  type: string;
  profitOrLoss: number;
}

const TRADEHIST: TradeHist[] = [
  {
    date: new Date(),
    symbol: 'TSLA',
    name: 'Tesla, Inc.',
    type: 'Sell',
    profitOrLoss: 200
  },
  {
    date: new Date(),
    symbol: 'TSLA',
    name: 'Tesla, Inc.',
    type: 'Sell',
    profitOrLoss: 200
  },
  {
    date: new Date(),
    symbol: 'TSLA',
    name: 'Tesla, Inc.',
    type: 'Sell',
    profitOrLoss: 200
  },
]

@Component({
  selector: 'app-trading-history',
  templateUrl: './trading-history.component.html',
  styleUrls: ['./trading-history.component.css']
})
export class TradingHistoryComponent implements OnInit {

  tradeHist = TRADEHIST;

  constructor() { }

  ngOnInit(): void {
  }

}
