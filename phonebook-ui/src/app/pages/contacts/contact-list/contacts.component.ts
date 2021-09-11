import {Component, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  contacts: Contact [] = [];

  constructor(private contactService: ContactService) {
  }

  ngOnInit(): void {
    console.log("ngOnInit");
    this.getContacts();
  }

  getContacts(): void {

    this.contactService.getContacts()
      .subscribe(value => this.contacts = value)
  }

  deleteContact(contact: Contact): void {

    this.contactService.deleteContact(contact.id).subscribe(
      _ => {
        this.contacts = this.contacts.filter(h => h !== contact);
      }
    );

  }
}
