import { EventEmitter } from '@angular/core';
import {Component, Input, Output} from '@angular/core';
import {Email} from "../../../../model/email";

@Component({
  selector: 'app-email-element',
  templateUrl: './email-element.component.html',
  styleUrls: ['./email-element.component.css']
})
export class EmailElementComponent {

  @Input()
  email: Email | undefined;
  @Output()
  childButtonClick = new EventEmitter<Email>();


  deleteEmail() {
    const toParent = this.email;
    this.childButtonClick.emit(toParent);
  }
}
