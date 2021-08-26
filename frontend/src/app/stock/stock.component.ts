import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestService } from '../rest-services';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})

export class StockComponent implements OnInit {

  symbol: any;
  name!: string;
  description!: string;
  currentPrice!: number;

  historicalPrice: any = {}

  constructor(private route: ActivatedRoute, private restService: RestService) {
    this.name = "Loading stock name";
    this.description = "loading stock description";
    this.currentPrice = 0;
  }

  fetchStockInfo(): void {
    this.restService.getSearchStock(this.symbol)
                    .subscribe((response: any) => {
                      console.log(response)
                      this.name = response.name;
                      this.currentPrice = response.price;
                      this.description = response.description;
                    })
  }

  fetchStockHistoricalPrice(): void {
    this.restService.getStockHistoricalPrice(this.symbol)
                    .subscribe((response: any) => {
                      console.log(response)

                      let x: any[] = []
                      let open: any[] = []
                      let close: any[] = []
                      let high: any[] = []
                      let low: any[] = []

                      response.forEach(function (row: any) {
                        x.push(row.date)
                        open.push(row.prices.open)
                        close.push(row.prices.close)
                        high.push(row.prices.high)
                        low.push(row.prices.low)
                      })
                      
                      this.historicalPrice["x"] = x
                      this.historicalPrice["open"] = open
                      this.historicalPrice["close"] = close
                      this.historicalPrice["high"] = high
                      this.historicalPrice["low"] = low

                      console.log(this.historicalPrice)

                    })
  }

  ngOnInit(): void {
    this.symbol = this.route.snapshot.paramMap.get("symbol");
    this.fetchStockInfo();
    this.fetchStockHistoricalPrice()
  }

}
