import {Component, Input} from '@angular/core';
import {Email} from "../../../../model/email";

@Component({
  selector: 'app-email-element',
  templateUrl: './email-element.component.html',
  styleUrls: ['./email-element.component.css']
})
export class EmailElementComponent {

  @Input()
  email: Email | undefined;
}
