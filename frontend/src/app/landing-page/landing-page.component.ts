import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest-services';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {
  userAsset: any;
  userHist: any;

  constructor(private restService: RestService) { }

  ngOnInit(): void {
    // this.restService.getUserAsset().subscribe(
    //   (data: any) => this.userAsset = {
    //     headers: Object.keys(data[0]),
    //     userData: data
    //   },
    //   (err: any) => console.log("Error")
    // )
    // this.restService.getUserHist().subscribe(
    //   (data: any) => this.userHist = data,
    //   (err: any) => console.log("Error")
    // )
  }

  name = "wuhu";

}
