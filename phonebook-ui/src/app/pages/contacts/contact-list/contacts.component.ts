import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";
import {Subscription} from "rxjs";
import {debounceTime} from "rxjs/operators";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit, OnDestroy {

  contacts!: Contact [];
  errorStatus : String |  undefined;
  subscriptions: Subscription[] = [];

  constructor(private contactService: ContactService) {
  }

  ngOnInit(): void {

    this.getContacts();
  }

  getContacts(): void {

    this.errorStatus = undefined;

    const getContactSubscribe = this.contactService.getContacts()
      .subscribe((value) => {
        this.contacts = value;
        },
        () => {
          this.errorStatus = 'something went wrong with loading contacts';
      });

    this.subscriptions.push(getContactSubscribe);
  }

  deleteContact(contact: Contact): void {

    const getDeleteContactSubscribe = this.contactService.deleteContact(contact.id)
      .subscribe(_ => {
          this.contacts = this.contacts.filter(h => h !== contact);
      },
      () => {
        this.errorStatus = 'something went wrong with delete process';
      });

    this.subscriptions.push(getDeleteContactSubscribe);
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }

}
