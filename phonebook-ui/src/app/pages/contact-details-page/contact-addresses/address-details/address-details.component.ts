import {Component, Input,} from '@angular/core';
import {Address} from "../../../../model/address";

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent {

  @Input()
  address: Address | undefined;

}
