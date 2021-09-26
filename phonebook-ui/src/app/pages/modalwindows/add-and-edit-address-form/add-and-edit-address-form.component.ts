import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
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
  profileForm: FormGroup | undefined;
  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];
  @Input()
  artOfForm: String | undefined;

  constructor(public activeModal: NgbActiveModal, private addressService: AddressService,
              private router: Router, private fb: FormBuilder) {
  }

  ngOnInit(): void {

    this.initForm();
    if (this.address)
      this.setFormValue();
  }

  private initForm() {

    this.profileForm = this.fb.group({
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
    this.profileForm?.controls.country.setValue(this.address?.country);
    this.profileForm?.controls.city.setValue(this.address?.city);
    this.profileForm?.controls.address.setValue(this.address?.address);
    this.profileForm?.controls.id.setValue(this.address?.id);
    this.profileForm?.controls.isFavorite.setValue(this.address?.isFavorite);
    this.profileForm?.controls.index.setValue(this.address?.index);
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

    const editAddress = this.profileForm?.value;

    const getEditAddressSubscribe = this.addressService.editAddress(editAddress)
      .subscribe(
        value => {
          this.activeModal.close(editAddress);
        },
        error => {
          this.errorStatus = 'something went wrong with process of editing your address';
        });

    this.subscriptions.push(getEditAddressSubscribe);
  }

  private addAddress() {

    const getAddAddressSubscribe = this.addressService.addAddress(this.profileForm?.value)
      .subscribe(value => {
          this.callBack(value)
        },
        error => {
          this.errorStatus = 'something went wrong with process of adding your address';
        });

    this.subscriptions.push(getAddAddressSubscribe);
  }

  private callBack(value: Address) {

    const url = `contacts/${value.contactId}`;
    this.activeModal.close();
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([url]));
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
