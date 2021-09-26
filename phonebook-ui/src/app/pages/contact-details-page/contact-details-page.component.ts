import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../model/contact";
import {ActivatedRoute} from "@angular/router";
import {ContactService} from "../../service/contact.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-contact-details-page',
  templateUrl: './contact-details-page.component.html',
  styleUrls: ['./contact-details-page.component.css']
})
export class ContactDetailsPageComponent implements OnInit, OnDestroy{

  contact: Contact | undefined;
  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.getContact();
  }

  private getContact(): void {

    this.errorStatus = undefined;

    const id = Number(this.route.snapshot.paramMap.get('id'));

    const getContactSubscribe = this.contactService.getContact(id)
      .subscribe((value) => {
          this.contact = value;
        },
        () => {
          this.errorStatus = 'something went wrong with loading contacts';
        });

    this.subscriptions.push(getContactSubscribe);
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }

}
