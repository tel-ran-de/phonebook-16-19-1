import {Component, Input, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() contact?: Contact;

  constructor() {
  }

  ngOnInit(): void {
    console.log("ngOnInit worked")
  }

  addEmail(): void {
    console.log("we added email");
  }

  addPhone(): void {
    console.log("we added phone");
  }

  addAddress(): void {
    console.log("we added address");
  }
}
