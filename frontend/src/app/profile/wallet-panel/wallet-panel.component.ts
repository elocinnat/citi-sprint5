import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/rest-services';

@Component({
  selector: 'app-wallet-panel',
  templateUrl: './wallet-panel.component.html',
  styleUrls: ['./wallet-panel.component.css']
})
export class WalletPanelComponent implements OnInit {

  @Input() currency!: any;

  withdrawDeposit: any;
  amount: any;
  
  constructor(private restService: RestService) { 
    this.withdrawDeposit = "DEPOSIT"
    this.amount = ""
  }

  makeTransaction(){
    // take in type (withdraw or deposit), amt ($$ to add or delete)
    
    let data = {
      "type": this.withdrawDeposit,
      qty: parseFloat(this.amount)
    }
    console.log("making deposit", data)

    this.restService.postTransaction(data)
                    .subscribe(
                      (response: any) => {
                        console.log(response)

                        if (response == 200) {
                          console.log("success yay")
                        } else {
                          console.log("UR FAILURE")
                        }

                        this.withdrawDeposit = "BUY"
                        this.amount = ""
                      },

                      (error: any) => {
                        
                        console.log(error)
                        this.withdrawDeposit = "BUY"
                        this.amount = ""

                      }
                    )
  }

  ngOnInit(): void {
  }

}
