import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ColorSchemeComponent } from './color-scheme/color-scheme.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { FirstComponent } from './landing-page/first/first.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatSliderModule} from "@angular/material/slider";

import {Routes, RouterModule, Router} from "@angular/router";
import { ProfileComponent } from './profile/profile.component';
import { StockComponent } from './stock/stock.component';

// external libraries
import { NgxEchartsModule } from 'ngx-echarts';

const routes: Routes = [
  {path: "", component: LandingPageComponent},
  {path: "color-scheme", component: ColorSchemeComponent},
  {path: "profile", component: ProfileComponent},
  {path: "stock", component: StockComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ColorSchemeComponent,
    LandingPageComponent,
    FirstComponent,
    ProfileComponent,
    StockComponent
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    RouterModule.forRoot(routes),
    NgxEchartsModule.forRoot({
      echarts: () => import('echarts')
    })
  ],

  exports: [
    RouterModule
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
