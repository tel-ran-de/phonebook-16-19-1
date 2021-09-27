import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Address} from "../model/address";

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private readonly addressPath = 'api/address';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  getAll(contactId: number): Observable<Address[]> {
    const url = `${this.addressPath}/${contactId}/all`;
    return this.httpClient.get<Address[]>(url)
  }

  deleteAddress(id: number): Observable<void> {
    const url = `${this.addressPath}/${id}`
    return this.httpClient.delete<void>(url);
  }

}
