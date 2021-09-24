import {Component, OnDestroy, OnInit} from '@angular/core';
import {Address} from "../../../model/address";
import {Subscription} from "rxjs";
import {AddressService} from "../../../service/address.serivce";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-contact-addresses',
  templateUrl: './contact-addresses.component.html',
  styleUrls: ['./contact-addresses.component.css']
})
export class ContactAddressesComponent implements OnInit, OnDestroy {
  addresses: Address[] | undefined;
  getAllAddressesErrorMessage: string | undefined;
  private subscriptions: Subscription[] = [];


  constructor(private addressService: AddressService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getAllAddresses();
  }

  open() {
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

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }
}
