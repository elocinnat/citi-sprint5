import { Component, OnInit } from '@angular/core';
import {ThemePalette} from "@angular/material/core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  links = ["Home", "Profile"];
  activeLink = this.links[0];
  background: ThemePalette = undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
