import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {

  @Input() data: any;

  constructor() { }

  // public graph = {
  //   data: [{
  //     values: [19, 26, 55],
  //     labels: ['Residential', 'Non-Residential', 'Utility'],
  //     type: 'pie'
  //   }],
  //   layout: { autosize: true, title: 'A Fancy Plot' },
  // };
  public pieLayout = {
    autosize: true, 
    title: 'Overview of Holdings',
    showlegend: false
  }

  ngOnInit(): void {
  }

}
