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

  getAll(addressId: number): Observable<Address[]> {
    const url = `${this.addressPath}/${addressId}/all`;
    return this.httpClient.get<Address[]>(url)
  }

  addAddress(address: Address): Observable<Address> {
    return this.httpClient.post<Address>(this.addressPath, address, this.httpOptions);
  }

  editAddress(address: Address): Observable<void> {

    const url = `${this.addressPath}/${address.id}`;
    return this.httpClient.put<void>(url, address, this.httpOptions);
  }

  deleteAddress(id: number): Observable<void> {
    const url = `${this.addressPath}/${id}`
    return this.httpClient.delete<void>(url);
  }
}
