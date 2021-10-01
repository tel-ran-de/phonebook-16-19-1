import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {Address} from "../../../model/address";
import {AddressService} from "../../../service/address.serivce";
import {httpErrorHandler} from "../../../httpErrorHandle";

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
  @Input()
  artOfForm: String | undefined;

  addressForm: FormGroup | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(public activeModal: NgbActiveModal,
              private addressService: AddressService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();

    if (this.address)
      this.setFormValue();
  }

  private initForm(): void {

    this.addressForm = this.fb.group({
      'id': [null],
      'country': [null, [Validators.required]],
      'city': [null, [Validators.required]],
      'address': [null, [Validators.required]],
      'index': [null, [Validators.required]],
      'isFavorite': [false],
      'contactId': [this.contactId],
    });
  }

  private setFormValue(): void {

    this.addressForm!.controls.country.setValue(this.address!.country);
    this.addressForm!.controls.city.setValue(this.address!.city);
    this.addressForm!.controls.address.setValue(this.address!.address);
    this.addressForm!.controls.id.setValue(this.address!.id);
    this.addressForm!.controls.isFavorite.setValue(this.address!.isFavorite);
    this.addressForm!.controls.index.setValue(this.address!.index);

    // alternative way to set all value
    // this.addressForm?.setValue(this.address!);
  }

  onSubmit(): void {

    this.errorMessage = undefined;

    if (this.address)
      this.editAddress();
    else
      this.addAddress();
  }

  private editAddress(): void {

    const editAddress = this.addressForm?.value;

    const getEditAddressSubscribe = this.addressService.edit(editAddress)
      .subscribe(_ => this.activeModal.close(editAddress),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getEditAddressSubscribe);
  }

  private addAddress(): void {

    const getAddAddressSubscribe = this.addressService.add(this.addressForm?.value)
      .subscribe(value => this.activeModal.close(value),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getAddAddressSubscribe);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
