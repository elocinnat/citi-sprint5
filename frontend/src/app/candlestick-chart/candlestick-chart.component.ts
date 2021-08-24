import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-candlestick-chart',
  templateUrl: './candlestick-chart.component.html',
  styleUrls: ['./candlestick-chart.component.css']
})

export class CandlestickChartComponent implements OnInit {

  @Input() data!: any;

  constructor() { }

  // public data:any = {
  //   x: [], //datetime goes here
  //   close: [],
  //   high: [],
  //   low: [],
  //   open: [],
  //   increasing: {line: {color: 'green'}}, 
  //   decreasing: {line: {color: 'red'}}, 
  //   line: {color: 'black'}, 
  //   type: 'candlestick', 
  //   xaxis: 'x', 
  //   yaxis: 'y'
  // }

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
      range: ['2017-01-03 12:00', '2017-02-15 12:00'], 
      rangeslider: {range: ['2017-01-03 12:00', '2017-02-15 12:00']}, 
      title: 'Date', 
      type: 'date'
    }, 
    yaxis: {
      autorange: true, 
      domain: [0, 1], 
      range: [114.609999778, 137.410004222], 
      type: 'linear'
  }

};

  ngOnInit(): void {
  }

}
