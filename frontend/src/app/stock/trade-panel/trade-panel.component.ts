import { Component, OnInit, Input } from '@angular/core';
import { RestService } from 'src/app/rest-services';

@Component({
  selector: 'app-trade-panel',
  templateUrl: './trade-panel.component.html',
  styleUrls: ['./trade-panel.component.css']
})
export class TradePanelComponent implements OnInit {

  @Input() symbol!: string
  @Input() currentPrice!: number

  buysell: any;
  limitmarket: any;
  quantity: any;

  constructor(private restService: RestService) { 
    this.buysell = "BUY"
    this.limitmarket = "limit"
    this.quantity = ""
  }

  makeTrade() {
    let data = {
      ticker: this.symbol,
      type: this.buysell, 
      qty: parseInt(this.quantity),
    }

    console.log(data)

    this.restService.postTrade(data)
                    .subscribe((response: any) => {
                      console.log(response)
                    })
  }

  ngOnInit(): void {
  }

}
