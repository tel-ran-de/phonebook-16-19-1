import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Contact} from "../model/contact";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private phoneBookUrl = 'host/contacts';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  getContacts(): Observable<Contact[]> {

    return this.http.get<Contact[]>(this.phoneBookUrl)
      .pipe(
        catchError(this.handleError<Contact[]>('getContacts', []))
      );
    ;

  }

  deleteContact(id: number): Observable<Contact> {
    const url = `${this.phoneBookUrl}/${id}`;

    return this.http.delete<Contact>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError<Contact>('deleteContact'))
      );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
