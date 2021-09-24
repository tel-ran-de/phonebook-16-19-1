import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private readonly phoneBookUrl = 'api/contact';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  getContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.phoneBookUrl);
  }

  deleteContact(id: number): Observable<void> {

    const url = `${this.phoneBookUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  getContact(id: number): Observable<Contact> {
    const url = `${this.phoneBookUrl}/${id}`;
    return this.http.get<Contact>(url);
  }

  addContact(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.phoneBookUrl, contact, this.httpOptions);
  }

  editContact(contact: Contact): Observable<void> {
    const url = `${this.phoneBookUrl}/${contact.id}`;
    return this.http.put<void>(url,contact, this.httpOptions);
  }

}
