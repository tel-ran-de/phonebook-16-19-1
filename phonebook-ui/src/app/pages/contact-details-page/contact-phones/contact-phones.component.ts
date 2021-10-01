import {Component, OnDestroy, OnInit} from '@angular/core';
import {Phone} from "../../../model/phone";
import {PhoneService} from "../../../service/phone.service";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {httpErrorHandler} from "../../../httpErrorHandle";
import {AddAndEditPhoneFormComponent} from "../../modalwindows/add-and-edit-phone-form/add-and-edit-phone-form.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-contact-phones',
  templateUrl: './contact-phones.component.html',
  styleUrls: ['./contact-phones.component.css']
})
export class ContactPhonesComponent implements OnInit, OnDestroy {

  phones: Phone[] | undefined;
  errorMessage: string | undefined;
  private subscriptions: Subscription[] = [];
  private contactId!: number;

  constructor(private phoneService: PhoneService,
              private route: ActivatedRoute,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get("id"));
    this.getAllPhones();
  }

  addPhone(): void {
    const modalRef = this.modalService.open(AddAndEditPhoneFormComponent);

    modalRef.componentInstance.artOfForm = "Add your phone";
    modalRef.componentInstance.contactId = this.contactId;

    modalRef.closed.subscribe(value => this.phones?.push(value));
  }

  private getAllPhones(): void {
    this.errorMessage = undefined;

    const getAllPhonesSubscription = this.phoneService.getAll(this.contactId)
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
