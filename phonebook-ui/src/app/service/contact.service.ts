import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private readonly phoneBookUrl = 'api/contacts';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  add(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.phoneBookUrl, contact, this.httpOptions);
  }

  edit(contact: Contact): Observable<void> {
    const url = `${this.phoneBookUrl}/${contact.id}`;
    return this.http.put<void>(url, contact, this.httpOptions);
  }

  getById(contactId: number): Observable<Contact> {
    const url = `${this.phoneBookUrl}/${contactId}`;
    return this.http.get<Contact>(url);
  }

  getAll(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.phoneBookUrl);
  }

  delete(contactId: number): Observable<void> {
    const url = `${this.phoneBookUrl}/${contactId}`;
    return this.http.delete<void>(url);
  }
}
