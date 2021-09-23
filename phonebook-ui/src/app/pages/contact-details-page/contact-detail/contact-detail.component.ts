import {Component, Input, OnInit} from '@angular/core';
import {Contact} from "../../../model/contact";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditContactFormComponent} from "../../modalwindows/add-and-edit-contact-form/add-and-edit-contact-form.component";

@Component({
  selector: 'app-contact-detail',
  templateUrl: './contact-detail.component.html',
  styleUrls: ['./contact-detail.component.css']
})
export class ContactDetailComponent implements OnInit {

  @Input() contact?: Contact;

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {

  }

  open() {

    const modalRef = this.modalService.open(AddAndEditContactFormComponent);
    modalRef.componentInstance.artOfForm = "Edit your contact";
    modalRef.componentInstance.contact = this.contact;
  }

}
