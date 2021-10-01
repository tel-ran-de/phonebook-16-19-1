import {Component, EventEmitter, Input, OnDestroy, Output,} from '@angular/core';
import {Phone} from "../../../../model/phone";
import {Subscription} from "rxjs";
import {PhoneService} from "../../../../service/phone.service";
import {httpErrorHandler} from "../../../../httpErrorHandle";

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

  constructor(private phoneService: PhoneService) {
  }

  onClickDelete() {

    this.errorMessage = undefined;

    const deletePhoneSubscribe = this.phoneService.deletePhone(this.phone!.id)
      .subscribe(_ => this.phoneDeleted.emit(this.phone),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(deletePhoneSubscribe)
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
