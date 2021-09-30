import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Email} from "../../../../model/email";
import {AddAndEditEmailFormComponent} from '../../../modalwindows/add-and-edit-email-form/add-and-edit-email-form.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-email-element',
  templateUrl: './email-element.component.html',
  styleUrls: ['./email-element.component.css']
})
export class EmailElementComponent {

  @Input()
  email: Email | undefined;
  @Output()
  childButtonClick: EventEmitter<Email> = new EventEmitter<Email>();

  constructor(private modalService: NgbModal) {
  }

  open() {
    const modalRef = this.modalService.open(AddAndEditEmailFormComponent);
    modalRef.componentInstance.artOfForm = "Edit your email";
    modalRef.componentInstance.email = this.email;
    modalRef.closed.subscribe(value => this.email = value);
  }

  deleteEmail() {
    const toParent = this.email;
    this.childButtonClick.emit(toParent);
  }
}
