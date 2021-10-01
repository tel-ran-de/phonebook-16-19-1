import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Address} from "../model/address";

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private readonly addressPath = 'api/addresses';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  add(address: Address): Observable<Address> {
    return this.httpClient.post<Address>(this.addressPath, address, this.httpOptions);
  }

  edit(address: Address): Observable<void> {
    const url = `${this.addressPath}/${address.id}`;
    return this.httpClient.put<void>(url, address, this.httpOptions);
  }

  getAll(contactId: number): Observable<Address[]> {
    const url = `${this.addressPath}/${contactId}/all`;
    return this.httpClient.get<Address[]>(url)
  }

  delete(addressId: number): Observable<void> {
    const url = `${this.addressPath}/${addressId}`
    return this.httpClient.delete<void>(url);
  }
}
