import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() data!: any;
  @Input() headers? :any

  constructor() { }

  ngOnInit(): void {
    console.log(this.data)
    if (!this.headers) {
      this.headers = Object.keys(this.data[0]);
    }
  }

}
