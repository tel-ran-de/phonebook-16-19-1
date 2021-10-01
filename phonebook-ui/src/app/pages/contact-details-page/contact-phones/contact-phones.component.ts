import {Component, OnDestroy, OnInit} from '@angular/core';
import {Phone} from "../../../model/phone";
import {PhoneService} from "../../../service/phone.service";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {Subscription} from "rxjs";
import {AddAndEditPhoneFormComponent} from "../../modalwindows/add-and-edit-phone-form/add-and-edit-phone-form.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-contact-phones',
  templateUrl: './contact-phones.component.html',
  styleUrls: ['./contact-phones.component.css']
})
export class ContactPhonesComponent implements OnInit, OnDestroy {
  phones: Phone[] | undefined;
  getAllPhonesErrorMessage: string | undefined;
  private subscriptions: Subscription[] = [];

  constructor(private phoneService: PhoneService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getAllPhones();
  }

  private getAllPhones() {
    this.getAllPhonesErrorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get("id"));

    const getAllPhonesSubscription = this.phoneService.getAll(contactId).subscribe(value => this.phones = value, error => this.callBackGetAllPhonesError(error))
    this.subscriptions.push(getAllPhonesSubscription);
  }

  private callBackGetAllPhonesError(error: HttpErrorResponse) {
    this.getAllPhonesErrorMessage = "Error";
  }

  deletePhone($event: Phone): void {
    this.phones = this.phones?.filter(value => value.id !== $event.id);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }
}
