import {Component, OnDestroy, OnInit} from '@angular/core';
import {Address} from "../../../model/address";
import {Subscription} from "rxjs";
import {AddressService} from "../../../service/address.serivce";
import {ActivatedRoute} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditAddressFormComponent} from "../../modalwindows/add-and-edit-address-form/add-and-edit-address-form.component";
import {httpErrorHandler} from "../../../httpErrorHandle";

@Component({
  selector: 'app-contact-addresses',
  templateUrl: './contact-addresses.component.html',
  styleUrls: ['./contact-addresses.component.css']
})
export class ContactAddressesComponent implements OnInit, OnDestroy {

  addresses: Address[] | undefined;
  errorMessage: string | undefined;

  private subscriptions: Subscription[] = [];
  private contactId!: number;

  constructor(private addressService: AddressService,
              private route: ActivatedRoute,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
    this.getAllAddresses();
  }

  openAddModalWindow(): void {
    const modalRef = this.modalService.open(AddAndEditAddressFormComponent);

    modalRef.componentInstance.contactId = this.contactId;
    modalRef.componentInstance.artOfForm = "Add your address";
    modalRef.closed.subscribe(value => this.addresses?.push(value));
  }

  private getAllAddresses(): void {
    this.errorMessage = undefined;

    const getAllAddressesSubscription = this.addressService.getAll(this.contactId)
      .subscribe(value => this.addresses = value,
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getAllAddressesSubscription);
  }

  deleteAddress($event: Address): void {
    this.addresses = this.addresses?.filter(value => value.id !== $event.id);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }
}
