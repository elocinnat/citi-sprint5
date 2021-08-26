import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-candlestick-chart',
  templateUrl: './candlestick-chart.component.html',
  styleUrls: ['./candlestick-chart.component.css']
})

export class CandlestickChartComponent implements OnInit {

  @Input() data!: any;

  constructor() { }

  public candleLayout = {
    dragmode: 'zoom', 
    margin: {
      r: 10, 
      t: 25, 
      b: 40, 
      l: 60
    }, 
    showlegend: false, 
    xaxis: {
      autorange: true, 
      domain: [0, 1], 
      range: ['2020-08-01 12:00', '2021-08-31 12:00'], 
      rangeslider: {range: ['2020-08-01 12:00', '2021-08-31 12:00']}, 
      title: 'Date', 
      type: 'date'
    }, 
    yaxis: {
      autorange: true, 
      domain: [0, 1], 
      type: 'linear'
  }

};

  ngOnInit(): void {
    this.data["increasing"] = {line: {color: 'green'}}
    this.data["decreasing"] = {line: {color: 'red'}}
    this.data["line"] = {color: "black"}
    this.data["type"] = "candlestick",
    this.data["xaxis"] = "x"
    this.data["yaxis"] = "y"
  }

}
