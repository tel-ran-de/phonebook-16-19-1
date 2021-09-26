import {Component, Input, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {AddAndEditEmailFormComponent} from "../../modalwindows/add-and-edit-email-form/add-and-edit-email-form.component";
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

    const modalRef = this.modalService.open(AddAndEditEmailFormComponent);
    modalRef.componentInstance.artOfForm = "Add your email";
    modalRef.componentInstance.contactId = this.contact?.id;
  }

  addPhone(): void {
  }

  addAddress(): void {
  }
}
