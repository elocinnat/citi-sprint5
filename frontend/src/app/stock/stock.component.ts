import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})

export class StockComponent implements OnInit {

  symbol: any;
  name: string = "";
  description: string = "";
  currentPrice: number = 0.0;
  historicalPrice: any;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.symbol = this.route.snapshot.paramMap.get("symbol");
    this.name = "Stock name"
    this.description = "stock description";
    this.currentPrice = 64000;

  }

}
