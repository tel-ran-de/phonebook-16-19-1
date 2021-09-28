import {Component, Input, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {AddAndEditAddressFormComponent} from "../../modalwindows/add-and-edit-address-form/add-and-edit-address-form.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {

  @Input() contact?: Contact;

  constructor(private modalService: NgbModal) {
  }

  addEmail(): void {
  }

  addPhone(): void {
  }

  addAddress(): void {
    const modalRef = this.modalService.open(AddAndEditAddressFormComponent);
    modalRef.componentInstance.artOfForm = "Add your address";
    modalRef.componentInstance.contactId = this.contact?.id
  }
}
