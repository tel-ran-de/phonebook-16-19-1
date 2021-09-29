import {Component, OnDestroy, OnInit} from '@angular/core';
import {Address} from "../../../model/address";
import {Subscription} from "rxjs";
import {AddressService} from "../../../service/address.serivce";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditAddressFormComponent} from "../../modalwindows/add-and-edit-address-form/add-and-edit-address-form.component";

@Component({
  selector: 'app-contact-addresses',
  templateUrl: './contact-addresses.component.html',
  styleUrls: ['./contact-addresses.component.css']
})
export class ContactAddressesComponent implements OnInit, OnDestroy {
  addresses: Address[] | undefined;
  getAllAddressesErrorMessage: string | undefined;
  private subscriptions: Subscription[] = [];


  constructor(private addressService: AddressService, private route: ActivatedRoute, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.getAllAddresses();
  }

  openAddModalWindow() {
    const contactId = Number(this.route.snapshot.paramMap.get('id'));
    const modalRef = this.modalService.open(AddAndEditAddressFormComponent);

    modalRef.componentInstance.contactId = contactId;
    modalRef.componentInstance.artOfForm = "Add your address";
    modalRef.closed.subscribe(value => this.addresses?.push(value));
  }

  private getAllAddresses() {
    this.getAllAddressesErrorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get("id"));

    const getAllAddressesSubscription = this.addressService.getAll(contactId)
      .subscribe(value => this.addresses = value, error => this.callBackGetAllAddressesError(error))
    this.subscriptions.push(getAllAddressesSubscription);
  }

  private callBackGetAllAddressesError(error: HttpErrorResponse) {
    this.getAllAddressesErrorMessage = "Error";

  }

  deleteAddress($event: Address): void {
    this.addresses = this.addresses?.filter(value => value.id !== $event.id);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }
}
