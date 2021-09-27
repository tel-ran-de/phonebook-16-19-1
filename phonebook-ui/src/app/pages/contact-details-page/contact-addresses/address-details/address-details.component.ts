import {Component, Input,} from '@angular/core';
import {Address} from "../../../../model/address";

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent implements OnDestroy {

  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];

  @Input()
  address: Address | undefined;
  @Output()
  addressDeleted: EventEmitter<Address> = new EventEmitter<Address>();

  constructor(private addressService: AddressService) {
  }

  onClickDelete() {
    this.errorStatus = undefined;
    const deleteAddressSubscribe = this.addressService.deleteAddress(this.address!.id)
      .subscribe(_ => this.addressDeleted.emit(this.address),
        () => this.errorStatus = "something went wrong deleting address");
    this.subscriptions.push(deleteAddressSubscribe)
  }

  ngOnDestroy(): void {
  }
}
