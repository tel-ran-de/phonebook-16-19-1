import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";
import {Subscription} from "rxjs";
import {httpErrorHandler} from "../../../httpErrorHandle";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit, OnDestroy {

  contacts: Contact [] | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(private contactService: ContactService) {
  }

  ngOnInit(): void {

    this.getContacts();
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
