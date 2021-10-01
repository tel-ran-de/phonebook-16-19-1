import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../model/contact";
import {ActivatedRoute} from "@angular/router";
import {ContactService} from "../../service/contact.service";
import {Subscription} from "rxjs";
import {httpErrorHandler} from "../../httpErrorHandle";

@Component({
  selector: 'app-contact-details-page',
  templateUrl: './contact-details-page.component.html',
  styleUrls: ['./contact-details-page.component.css']
})
export class ContactDetailsPageComponent implements OnInit, OnDestroy {

  contact: Contact | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.getContact();
  }

  private getContact(): void {

    this.errorMessage = undefined;

    const id = Number(this.route.snapshot.paramMap.get('id'));

    const getContactSubscribe = this.contactService.getById(id)
      .subscribe(value => this.contact = value,
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getContactSubscribe);
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }

}
