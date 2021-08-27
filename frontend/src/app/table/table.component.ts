import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() data!: any;

  headers: any;

  constructor() { }

  ngOnInit(): void {
    
    this.headers = Object.keys(this.data[0]);
    console.log(this.data)
  }

}
