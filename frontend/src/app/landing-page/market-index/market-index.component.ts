import { Component, OnInit } from '@angular/core';
import {RestService} from "../../rest-services";

@Component({
  selector: 'app-market-index',
  templateUrl: './market-index.component.html',
  styleUrls: ['./market-index.component.css']
})

export class MarketIndexComponent implements OnInit {

  metaData:any;

  priceData:any = {
    x: [],
    open: [],
    close: [],
    high: [],
    low: [],
  }

  currentPrice: any;
  priceChange: any;
  pricePercentageChange: any;

  constructor(private restService: RestService) {}

  setHistoricalPrices() {

    this.restService.getSnp500()
              .subscribe((response: any) => {
                this.priceData.x = response.data.date;
                this.priceData.open = response.data.open;
                this.priceData.close =  response.data.close;
                this.priceData.high = response.data.high;
                this.priceData.low = response.data.low;

                this.metaData = response.meta;

                this.currentPrice = this.priceData.close[this.priceData.close.length-1]

                this.priceChange = this.currentPrice - this.priceData.close[this.priceData.close.length-2]
                this.priceChange = Math.round(this.priceChange * 100) / 100;

                this.pricePercentageChange = this.priceChange / this.currentPrice
                this.pricePercentageChange = Math.round(this.pricePercentageChange * 10000) / 100
              })

  }

  ngOnInit(): void {
    this.setHistoricalPrices()
  }

}
