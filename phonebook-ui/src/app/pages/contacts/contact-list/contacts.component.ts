import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";
import {Subscription} from "rxjs";
import {httpErrorHandler} from "../../../httpErrorHandle";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit, OnDestroy {

  contacts: Contact [] | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(private contactService: ContactService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {

    const queryParamOfRout = this.activatedRoute.snapshot.queryParamMap.get('searchContact') ;

    if(queryParamOfRout !== null){
      this.searchContact(queryParamOfRout);
    }else {
      this.getContacts();
    }
  }

  private searchContact(term: string):void{

    this.errorMessage = undefined;
    const searchContactSubscribe = this.contactService.searchContacts(term)
      .subscribe(value => {
        if(value.length !== 0) {
          this.contacts = value;
        }
        else {
          this.errorMessage = 'There is no Contacts'}
      });

    this.subscriptions.push(searchContactSubscribe);
  }

  private getContacts(): void {

    this.errorMessage = undefined;

    const getContactSubscribe = this.contactService.getAll()
      .subscribe(value => this.contacts = value,
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getContactSubscribe);
  }

  deleteContact(contact: Contact): void {

    this.errorMessage = undefined;

    const deleteContactSubscribe = this.contactService.delete(contact!.id)
      .subscribe(_ => this.contacts = this.contacts!.filter(h => h !== contact),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(deleteContactSubscribe);
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
