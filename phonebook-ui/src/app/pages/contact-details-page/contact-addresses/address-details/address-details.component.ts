import {Component, Input,} from '@angular/core';
import {Address} from "../../../../model/address";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditAddressFormComponent} from "../../../modalwindows/add-and-edit-address-form/add-and-edit-address-form.component";

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent {

  @Input()
  address: Address | undefined;

  constructor(private modalService: NgbModal) {
  }

  open() {
    const modalRef = this.modalService.open(AddAndEditAddressFormComponent);

    modalRef.componentInstance.address = this.address;
    modalRef.componentInstance.artOfForm = "Edit your address"
    modalRef.closed.subscribe(value => this.address = value);

  }
}
