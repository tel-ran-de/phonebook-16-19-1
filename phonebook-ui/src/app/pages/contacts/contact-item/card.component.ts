import {Component, Input, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {

  @Input() contact?: Contact;

  addEmail(): void {
  }

  addPhone(): void {
  }

  addAddress(): void {
  }
}
