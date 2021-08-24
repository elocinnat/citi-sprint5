import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-market-index',
  templateUrl: './market-index.component.html',
  styleUrls: ['./market-index.component.css']
})

export class MarketIndexComponent implements OnInit {

  bitcoinUrl:string;
  bitcoinPrice:any;

  arkkUrl:string;
  arkkData:any;
  arkkGraph:any;

  constructor(private http: HttpClient) {
    this.bitcoinUrl = "https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=sgd&days=1";
    // this.arkkUrl = "https://api.twelvedata.com/time_series?symbol=ARKK&interval=1day&format=JSON&dp=1&start_date=2021-01-01%2010:50:00&end_date=2021-08-24%2010:50:00&apikey=ff8c3fc972994f8db25dc2d74fe7f706";
    this.arkkUrl = "http://localhost:8000/arkk"
  }

  setHistoricalPrices() {
    this.http.get<any>(this.bitcoinUrl)
                    .subscribe((data) => {
                      this.bitcoinPrice = data
                      console.log("bitcoin price", this.bitcoinPrice)
                    })

    this.http.get<any>(this.arkkUrl)
                    .subscribe((data) => {
                      
                      let out:any = {
                        x: [],
                        close: [],
                        high: [],
                        low: [],
                        open: [],
                        increasing: {line: {color: 'green'}}, 
                        decreasing: {line: {color: 'red'}}, 
                        line: {color: 'black'}, 
                        type: 'candlestick', 
                        xaxis: 'x', 
                        yaxis: 'y'
                      }

                      data.values.forEach(function (val: any) {
                        out.x.push(val.datetime)
                        out.close.push(val.close)
                        out.high.push(val.high)
                        out.low.push(val.low)
                        out.open.push(val.open)
                      });

                      this.arkkData = [out]

                    })
  }

  ngOnInit(): void {
    this.setHistoricalPrices()
  }

}
