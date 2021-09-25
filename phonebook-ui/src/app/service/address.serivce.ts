import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Address} from "../model/address";
import {Contact} from "../model/contact";

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

  addAddress(address: Address): Observable<Address> {
    return this.httpClient.post<Address>(this.addressPath, address, this.httpOptions);
  }

  editAddress(address: Address): Observable<void>{

    const url = `${this.addressPath}/${address.id}`;
    return this.httpClient.put<void>(url,address,this.httpOptions);
  }

}
