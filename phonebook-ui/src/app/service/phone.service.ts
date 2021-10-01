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

  addPhone(phone: Phone): Observable<Phone> {
    return this.httpClient.post<Phone>(this.phonePath, phone, this.httpOptions);
  }

  editPhone(phone: Phone): Observable<void> {
    const url = `${this.phonePath}/${phone.id}`;
    return this.httpClient.put<void>(url, phone, this.httpOptions);
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
