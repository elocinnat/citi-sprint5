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
  current_price: number = 0.0;
  historical_price: any;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.symbol = this.route.snapshot.paramMap.get("symbol");
    this.name = "Bitcoin"
    this.description = "bitcoin description";
    this.current_price = 64000;

  }

}
