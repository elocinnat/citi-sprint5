import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest-services';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currency: any;
  userHist: any;
  userAsset: any;

  constructor(private restService: RestService) { }

  ngOnInit(): void {

    this.restService.getUser().subscribe(
      (data: any) => this.currency = data,
      (err: any) => console.log("Error")
    );

    this.restService.getUserHist().subscribe(
      (data: any) => this.userHist = data,
      (err: any) => console.log("Error")
    )

    this.restService.getUserAsset().subscribe(
      (data: any) => this.userAsset = data,
      (err: any) => console.log("Error")
    )
  }

}
