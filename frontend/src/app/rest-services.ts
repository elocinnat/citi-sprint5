import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: "root",
})
export class RestService {
    private backendUrl = "http://localhost:8080";
    private pythonBackend = "http://localhost:8000";

    constructor(private http: HttpClient) {}

    


}