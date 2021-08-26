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

  pieData: any = {
    values: [],
    labels: [],
    textinfo: "label+percent",
    textposition: "outside",
    automargin: true,
    type: 'pie'
  }

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
      (data: any) => {
        this.userAsset = data;
        for (var i = 0; i < data.length; i++) {
          this.pieData.values.push(data[i].valuation);
          this.pieData.labels.push(data[i].ticker)
        }
      },
      (err: any) => console.log("Error")
    )
  }

}
