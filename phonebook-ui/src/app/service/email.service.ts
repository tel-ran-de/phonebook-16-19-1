import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Email} from "../model/email";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private readonly emailPath = 'api/email';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  getAll(contactId: number): Observable<Email[]> {
    //url for facked backend
    const url = `${this.emailPath}?contactId=${contactId}`;
    //url for our backend
    // const url = `${this.emailUrl}/${contactId}`;
    return this.httpClient.get<Email[]>(url)
  }
}
