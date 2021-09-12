import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  readonly phoneBookUrl = 'host/contacts';

  httpOptions = {

    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  getContacts(): Observable<Contact[]> {

    return this.http.get<Contact[]>(this.phoneBookUrl);
  }

  deleteContact(id: number): Observable<void>{

    const url = `${this.phoneBookUrl}/${id}`;
    return this.http.delete<void>(url, this.httpOptions);
  }

}
