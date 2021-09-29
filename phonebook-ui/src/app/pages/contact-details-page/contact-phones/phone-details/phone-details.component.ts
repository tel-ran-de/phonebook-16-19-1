import {Component, EventEmitter, Input, OnDestroy, Output,} from '@angular/core';
import {Phone} from "../../../../model/phone";
import {Subscription} from "rxjs";
import {PhoneService} from "../../../../service/phone.service";

@Component({
  selector: 'app-phone-details',
  templateUrl: './phone-details.component.html',
  styleUrls: ['./phone-details.component.css']
})
export class PhoneDetailsComponent implements OnDestroy {

  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];

  @Input()
  phone: Phone | undefined;
  @Output()
  phoneDeleted: EventEmitter<Phone> = new EventEmitter<Phone>();

  constructor(private phoneService: PhoneService) {
  }

  onClickDelete() {
    this.errorStatus = undefined;
    const deletePhoneSubscribe = this.phoneService.deletePhone(this.phone!.id)
      .subscribe(_ => this.phoneDeleted.emit(this.phone),
        () => this.errorStatus = "something went wrong deleting phone");
    this.subscriptions.push(deletePhoneSubscribe)
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
