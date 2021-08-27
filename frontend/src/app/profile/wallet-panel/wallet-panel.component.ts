import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/rest-services';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-wallet-panel',
  templateUrl: './wallet-panel.component.html',
  styleUrls: ['./wallet-panel.component.css']
})
export class WalletPanelComponent implements OnInit {

  @Input() currency!: any;

  withdrawDeposit: any;
  amount: any;
  
  constructor(private restService: RestService, private _snackBar: MatSnackBar) { 
    this.withdrawDeposit = "DEPOSIT"
    this.amount = ""
  }

  openSnackBar(message: string) {
    this._snackBar.open(message)
  }

  openSnackBarAction(message: string, action: string) {
    this._snackBar.open(message, action)
  }

  makeTransaction(){
    // take in type (withdraw or deposit), amt ($$ to add or delete)
    
    let data = {
      "type": this.withdrawDeposit,
      qty: parseFloat(this.amount)
    }

    console.log(this.withdrawDeposit)

    this.openSnackBar(`Processing your ${this.withdrawDeposit.toLowerCase()}`)

    this.restService.postTransaction(data)
                    .subscribe(
                      (response: any) => {
                        console.log(response)

                        if (response == 200) {
                          console.log("success yay")
                        } else {
                          console.log("UR FAILURE")
                        }

                        this._snackBar.dismiss()
                        this.openSnackBarAction(this.withdrawDeposit.toLowerCase() + " success!", "close")

                        window.location.reload()

                        this.withdrawDeposit = "BUY"
                        this.amount = ""
                      },

                      (error: any) => {
                        
                        console.log(error)

                        this._snackBar.dismiss()
                        this.openSnackBarAction(this.withdrawDeposit.toLowerCase() + " failed", "close")

                        this.withdrawDeposit = "BUY"
                        this.amount = ""

                      }
                    )
  }

  ngOnInit(): void {
  }

}
