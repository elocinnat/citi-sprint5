import { Component, OnInit, Input, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-dialogs',
  templateUrl: './dialogs.component.html',
  styleUrls: ['./dialogs.component.css']
})
export class DialogsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data:any) {}

  ngOnInit(): void {
    console.log("from dialog", this.data)
  }

}
