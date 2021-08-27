import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/rest-services';

@Component({
  selector: 'app-wallet-panel',
  templateUrl: './wallet-panel.component.html',
  styleUrls: ['./wallet-panel.component.css']
})
export class WalletPanelComponent implements OnInit {

  @Input()
  currency: any;

  withdrawDeposit: any;
  amount: any;
  
  constructor(private restService: RestService) { 
    this.withdrawDeposit = "DEPOSIT"
    this.amount = ""
  }

  makeDeposit(){
    // take in type (withdraw or deposit), amt ($$ to add or delete)
    
    let data = {
      "type": this.withdrawDeposit,
      amt: parseInt(this.amount)
    }
    console.log("making deposit", data)

    // this.restService.postDeposit(data).subscribe()
  }
  makeWithdraw(){

  }

  ngOnInit(): void {
  }

}
