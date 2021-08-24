import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { ProfileComponent } from './profile/profile.component';
import { StockComponent } from './stock/stock.component';

const routes: Routes = [
  { path: '',        component: LandingPageComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'stock',   component: StockComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
