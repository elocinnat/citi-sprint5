import { Component, OnInit } from '@angular/core';
import { FormControl, ReactiveFormsModule } from "@angular/forms";
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { RestService } from 'src/app/rest-services';

export interface Stock {
  name: string,
  symbol: string
}

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {

  selectedStock!: Stock;

  options: Stock[] = [
    {name: "Apple", symbol: "AAPL"},
    {name: "Amazon", symbol: "AMZN"}
  ]
  filteredOptions?: Observable<Stock[]>;
  public control: FormControl

  constructor(private restService: RestService) {
    let control: FormControl = new FormControl()
    this.control = control
  }

  private _filter(name: string): Stock[] {
    const filterValue = name.toLowerCase();

    return this.options.filter(
      option => option.name.toLowerCase().includes(filterValue)
    );
  }

  displayFunction(stock: Stock): string {
    try {
      return `${stock.name} (${stock.symbol})`
    } catch {
      return ""
    }
  }

  ngOnInit(): void {

    this.restService.getStockList()
                    .subscribe((response: any) => {

                      this.options = response;

                      console.log(this.options)

                      this.filteredOptions = this.control.valueChanges
                      .pipe(
                        startWith(''),
                        map(value => typeof value === 'string' ? value : value.name),
                        map(name => name ? this._filter(name) : this.options.slice())
                      );
                    })


  }

}
