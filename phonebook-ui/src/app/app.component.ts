import { Component } from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditContactFormComponent} from "./pages/modalwindows/add-and-edit-contact-form/add-and-edit-contact-form.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private check:String | undefined;
  constructor(private modalService: NgbModal) {
  }

  open() {
    const modalRef = this.modalService.open(AddAndEditContactFormComponent);
    this.check = modalRef.componentInstance.contact.firstName;
    console.log("halo");

  }
}
