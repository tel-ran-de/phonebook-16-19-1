import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {Address} from "../../../model/address";
import {AddressService} from "../../../service/address.serivce";

@Component({
  selector: 'app-add-and-edit-address-form',
  templateUrl: './add-and-edit-address-form.component.html',
  styleUrls: ['./add-and-edit-address-form.component.css']
})
export class AddAndEditAddressFormComponent implements OnInit, OnDestroy {

  @Input()
  address: Address | undefined;
  @Input()
  contactId: number | undefined;
  addressForm: FormGroup | undefined;
  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];
  @Input()
  artOfForm: String | undefined;

  constructor(public activeModal: NgbActiveModal, private addressService: AddressService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {

    this.initForm();
    if (this.address)
      this.setFormValue();
  }

  private initForm() {

    this.addressForm = this.fb.group({
      'id': [null],
      'country': [null, [Validators.required]],
      'city': [null, [Validators.required]],
      'address': [null, [Validators.required]],
      'index': [null, [Validators.required]],
      'isFavorite': [null],
      'contactId': [this.contactId],
    });
  }

  private setFormValue() {

    this.addressForm!.controls.country.setValue(this.address!.country);
    this.addressForm!.controls.city.setValue(this.address!.city);
    this.addressForm!.controls.address.setValue(this.address!.address);
    this.addressForm!.controls.id.setValue(this.address!.id);
    this.addressForm!.controls.isFavorite.setValue(this.address!.isFavorite);
    this.addressForm!.controls.index.setValue(this.address!.index);
    // alternative way to set all value
    // this.addressForm?.setValue(this.address!);

  }

  onSubmit() {

    this.errorStatus = undefined;

    if (this.artOfForm === "Edit your address") {
      this.editAddress();
    } else {
      this.addAddress();
    }
  }

  private editAddress() {

    const editAddress = this.addressForm?.value;

    const getEditAddressSubscribe = this.addressService.editAddress(editAddress)
      .subscribe(
        value =>
          this.activeModal.close(editAddress)
        ,
        error =>
          this.errorStatus = 'something went wrong with process of editing your address'
      );

    this.subscriptions.push(getEditAddressSubscribe);
  }

  private addAddress() {

    const getAddAddressSubscribe = this.addressService.addAddress(this.addressForm?.value)
      .subscribe(value =>
          this.activeModal.close(value)
        ,
        error =>
          this.errorStatus = 'something went wrong with process of adding your address'
      );

    this.subscriptions.push(getAddAddressSubscribe);
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
