import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Phone} from "../../../model/phone";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {PhoneService} from "../../../service/phone.service";
import {COUNTRIES} from "./countries";
import {Country} from "../../../model/country";
import {httpErrorHandler} from "../../../httpErrorHandle";

@Component({
  selector: 'app-add-and-edit-phone-form',
  templateUrl: './add-and-edit-phone-form.component.html',
  styleUrls: ['./add-and-edit-phone-form.component.css']
})
export class AddAndEditPhoneFormComponent implements OnInit, OnDestroy {

  @Input()
  phone: Phone | undefined;
  @Input()
  contactId: number | undefined;
  @Input()
  artOfForm: String | undefined;

  phoneForm: FormGroup | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  countriesList: Country[] = COUNTRIES;
  selectedCountryCode = 'DE'

  constructor(public activeModal: NgbActiveModal,
              private phoneService: PhoneService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.phone)
      this.setFormValue();
  }

  private initForm() {
    const selectedCountry = this.countriesList.find(value => value.code === this.selectedCountryCode.toUpperCase());
    let defaultCC: Country | null = selectedCountry ? selectedCountry : null;

    this.phoneForm = this.fb.group({
      'id': [null],
      'countryCode': [defaultCC?.dial_code, [Validators.required]],
      'telephoneNumber': [null, [Validators.required, Validators.pattern('^[0-9]+$'), Validators.maxLength(30), Validators.minLength(5)]],
      'isFavorite': [false],
      'contactId': [this.contactId]
    });
  }

  private setFormValue() {
    this.phoneForm!.controls.id.setValue(this.phone!.id);
    this.phoneForm!.controls.countryCode.setValue(this.phone!.countryCode);
    this.phoneForm!.controls.telephoneNumber.setValue(this.phone!.telephoneNumber);
    this.phoneForm!.controls.isFavorite.setValue(this.phone!.isFavorite);
    this.phoneForm!.controls.contactId.setValue(this.contactId);
  }

  onSubmit() {

    this.errorMessage = undefined;

    if (this.phone)
      this.editPhone();
    else
      this.addPhone();
  }

  private editPhone() {
    const editPhone = this.phoneForm?.value;

    const getEditPhoneSubscribe = this.phoneService.editPhone(editPhone)
      .subscribe(_ => this.activeModal.close(editPhone),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getEditPhoneSubscribe);
  }

  private addPhone() {

    const addPhoneSubscribe = this.phoneService.addPhone(this.phoneForm?.value)
      .subscribe(value => this.activeModal.close(value),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(addPhoneSubscribe);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
