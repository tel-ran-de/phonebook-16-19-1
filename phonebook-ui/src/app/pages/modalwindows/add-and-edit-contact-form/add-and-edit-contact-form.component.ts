import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";

@Component({
  selector: 'app-add-and-edit-contact-form',
  templateUrl: './add-and-edit-contact-form.component.html',
  styleUrls: ['./add-and-edit-contact-form.component.css']
})
export class AddAndEditContactFormComponent implements OnInit, OnDestroy {

  contacts : Contact [] = [];

  @Input() contact : Contact = {id: 1,
    firstName: '',
    lastName: '',
    age: 0,
    isFavorite: true,
    group: ''};

  @Input() name: String | undefined ;

  constructor(public activeModal: NgbActiveModal,
              private contactService: ContactService) {}

  ngOnInit(): void {
    this.getContacts();
  }

  getContacts(): void {

    const getContactsSubscribe = this.contactService.getContacts()
      .subscribe((value) => {
          this.contacts = value;
        },);

  }

  ngOnDestroy(){
    console.log(this.contact.firstName);
    console.log(this.contact.lastName);
    console.log(this.contact.age);
    console.log(this.contact.isFavorite);
    console.log(this.contact.group);
    this.contactService.addContact(this.contact)
      .subscribe();

  }


}
