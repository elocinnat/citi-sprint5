import { Component, OnInit, Input } from '@angular/core';
import { RestService } from 'src/app/rest-services';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogsComponent } from './dialogs/dialogs.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { stringify } from '@angular/compiler/src/util';

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

  constructor(private restService: RestService, public dialog: MatDialog, private _snackBar: MatSnackBar) { 
    this.buysell = "BUY"
    this.limitmarket = "limit"
    this.quantity = ""
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(DialogsComponent, {
      width: "30%",
      data: data
    })
  }

  closeDialog(): void {
    this.dialog.closeAll();
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message)
  }

  makeTrade() {

    let data = {
      "type": this.buysell, 
      qty: parseInt(this.quantity),
      ticker: this.symbol,
    }

    console.log("making trade", data)

    if (!(data.qty > 0)) {
      this.openDialog({
        dialogType: "invalid"
      })
      return
    }

    this.openSnackBar("Processing your trade", "close")

    this.restService.postTrade(data)
                    .subscribe(
                      (response: any) => {
                        this._snackBar.dismiss()
                        this.closeDialog()
                        console.log(response)

                        if (response == 200) {
                          this.openDialog({
                            dialogType: "success",
                            tradeData: data
                          })
                        } else {
                          this.openDialog({
                            dialogType: "failure",
                            tradeDate: data
                          })
                        }

                        this.buysell = "BUY"
                        this.limitmarket = "limit"
                        this.quantity = ""
                      },

                      (error: any) => {
                        this._snackBar.dismiss()
                        this.closeDialog()
                        console.log(error)

                        this.openDialog({
                          dialogType: "failure",
                          tradeDate: data
                        })

                        this.buysell = "BUY"
                        this.limitmarket = "limit"
                        this.quantity = ""

                      }
                    )
  }

  ngOnInit(): void {

  }

}
