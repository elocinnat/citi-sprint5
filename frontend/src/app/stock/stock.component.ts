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
  historicalPrice: any;

  constructor(private route: ActivatedRoute, private restService: RestService) {}

  ngOnInit(): void {
    this.symbol = this.route.snapshot.paramMap.get("symbol");
    this.name = "Stock name"
    this.description = "stock description";
    this.currentPrice = 64000;

    this.restService.getSearchStock(this.symbol)
                    .subscribe((response: any) => {
                      console.log(response)
                      this.name = response.name;
                      this.currentPrice = response.price;
                      this.description = response.description;
                    })

  }

}
