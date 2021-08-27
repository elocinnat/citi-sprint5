import { Component, OnInit, Input } from '@angular/core';
import { RestService } from 'src/app/rest-services';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogsComponent } from './dialogs/dialogs.component';
<<<<<<< HEAD
import { MatSnackBar } from '@angular/material/snack-bar';
import { stringify } from '@angular/compiler/src/util';
=======
>>>>>>> new_dn_backend

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

<<<<<<< HEAD
  constructor(private restService: RestService, public dialog: MatDialog, private _snackBar: MatSnackBar) { 
=======
  constructor(private restService: RestService, public dialog: MatDialog) { 
>>>>>>> new_dn_backend
    this.buysell = "BUY"
    this.limitmarket = "limit"
    this.quantity = ""
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(DialogsComponent, {
<<<<<<< HEAD
      width: "30%",
=======
      width: "50%",
>>>>>>> new_dn_backend
      data: data
    })
  }

  closeDialog(): void {
    this.dialog.closeAll();
  }

<<<<<<< HEAD
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message)
  }

=======
>>>>>>> new_dn_backend
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
<<<<<<< HEAD
      return
    }

    this.openSnackBar("Processing your trade", "close")
=======

      return
    }

    this.openDialog({
      dialogType: "processing",
      tradeData: data
    })
>>>>>>> new_dn_backend

    this.restService.postTrade(data)
                    .subscribe(
                      (response: any) => {
<<<<<<< HEAD
                        this._snackBar.dismiss()
=======
>>>>>>> new_dn_backend
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
<<<<<<< HEAD
                        this._snackBar.dismiss()
=======
>>>>>>> new_dn_backend
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
