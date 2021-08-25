import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-gainer-loser-row',
  templateUrl: './gainer-loser-row.component.html',
  styleUrls: ['./gainer-loser-row.component.css']
})
export class GainerLoserRowComponent implements OnInit {

  @Input() data!: any;
  @Input() color!: any;
  @Input() icon!: any;

  constructor() { }

  ngOnInit(): void {
  }

}
