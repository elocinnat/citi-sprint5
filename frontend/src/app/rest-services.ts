import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: "root",
})
export class RestService {
    
    private backendUrl = "http://spring-boot:8081";


    constructor(private http: HttpClient) {}

    getSnp500(): any {
        // return this.http.get(this.pythonBackend + "/snp500")
        return this.http.get("https://api.jsonbin.io/b/61285590c5159b35ae048beb")
    }

    getUser(): any {
        return this.http.get(this.backendUrl + "/user") as Observable<string>
    }

    getUserAsset(): any {
        return this.http.get(this.backendUrl + "/user/asset")
    }

    getUserHist(): any {
        return this.http.get(this.backendUrl + '/user/history')
    }

    getSearchStock(symbol: string): any {
        return this.http.get(this.backendUrl + "/search/" + symbol)
    }

    getStockHistoricalPrice(symbol: string): any {
        return this.http.get(this.backendUrl + "/historical-prices/" + symbol)
    }

    postTrade(data: any): any {
        let url:string = this.backendUrl + "/trade"
        return this.http.post<any>(url, data)
    }

    getStockList(): any {
        // return this.http.get(this.pythonBackend + "/stock/search")
        return this.http.get("https://api.jsonbin.io/b/61285612076a223676b1cb98/1")
    }

}