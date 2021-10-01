import {Component, OnDestroy, OnInit} from '@angular/core';
import {Phone} from "../../../model/phone";
import {PhoneService} from "../../../service/phone.service";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {httpErrorHandler} from "../../../httpErrorHandle";

@Component({
  selector: 'app-contact-phones',
  templateUrl: './contact-phones.component.html',
  styleUrls: ['./contact-phones.component.css']
})
export class ContactPhonesComponent implements OnInit, OnDestroy {
  phones: Phone[] | undefined;
  errorMessage: string | undefined;
  private subscriptions: Subscription[] = [];

  constructor(private phoneService: PhoneService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getAllPhones();
  }

  open() {
  }

  private getAllPhones(): void {
    this.errorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get("id"));

    const getAllPhonesSubscription = this.phoneService.getAll(contactId)
      .subscribe(value => this.phones = value,
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getAllPhonesSubscription);
  }

  deletePhone($event: Phone): void {
    this.phones = this.phones?.filter(value => value.id !== $event.id);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
