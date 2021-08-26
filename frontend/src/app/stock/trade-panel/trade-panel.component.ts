import { Component, OnInit, Input } from '@angular/core';
import { RestService } from 'src/app/rest-services';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

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

  constructor(private restService: RestService, public dialog: MatDialog) { 
    this.buysell = "BUY"
    this.limitmarket = "limit"
    this.quantity = ""
  }

  openDialog(): void {
    
  }


  makeTrade() {

    this.openDialog()

    let data = {
      "type": this.buysell, 
      qty: parseInt(this.quantity),
      ticker: this.symbol,
    }

    this.restService.postTrade(data)
                    .subscribe((response: any) => {
                      if (response==200) {

                      } else {

                      }
                    })
  }

  ngOnInit(): void {
  }

}
