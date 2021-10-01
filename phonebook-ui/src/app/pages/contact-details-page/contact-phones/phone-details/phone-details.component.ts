import {Component, EventEmitter, Input, OnDestroy, Output,} from '@angular/core';
import {Phone} from "../../../../model/phone";
import {Subscription} from "rxjs";
import {PhoneService} from "../../../../service/phone.service";
import {httpErrorHandler} from "../../../../httpErrorHandle";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditPhoneFormComponent} from "../../../modalwindows/add-and-edit-phone-form/add-and-edit-phone-form.component";

@Component({
  selector: 'app-phone-details',
  templateUrl: './phone-details.component.html',
  styleUrls: ['./phone-details.component.css']
})
export class PhoneDetailsComponent implements OnDestroy {

  @Input()
  phone: Phone | undefined;
  @Output()
  phoneDeleted: EventEmitter<Phone> = new EventEmitter<Phone>();

  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(private phoneService: PhoneService, private modalService: NgbModal) {
  }

  onClickDelete() {

    this.errorMessage = undefined;

    const deletePhoneSubscribe = this.phoneService.deletePhone(this.phone!.id)
      .subscribe(_ => this.phoneDeleted.emit(this.phone),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(deletePhoneSubscribe)
  }

  onClickEdit(): void {
    const modalRef = this.modalService.open(AddAndEditPhoneFormComponent);
    modalRef.componentInstance.artOfForm = "Edit your phone";
    modalRef.componentInstance.phone = this.phone;
    modalRef.closed.subscribe(value => this.phone = value);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
