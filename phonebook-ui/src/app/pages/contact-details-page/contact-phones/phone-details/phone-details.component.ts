import {Component, Input,} from '@angular/core';
import {Phone} from "../../../../model/phone";

@Component({
  selector: 'app-phone-details',
  templateUrl: './phone-details.component.html',
  styleUrls: ['./phone-details.component.css']
})
export class PhoneDetailsComponent {

  @Input()
  phone: Phone | undefined;

}
