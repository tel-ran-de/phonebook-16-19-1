import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Phone} from "../model/phone";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  private readonly phonePath = 'api/phones';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  getAll(contactId: number): Observable<Phone[]> {
    const url = `${this.phonePath}/${contactId}/all`;
    return this.httpClient.get<Phone[]>(url)
  }

  deletePhone(phoneId: number): Observable<void> {
    const url = `${this.phonePath}/${phoneId}`
    return this.httpClient.delete<void>(url);
  }
}
