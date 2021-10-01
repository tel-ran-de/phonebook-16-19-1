import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Phone} from "../../../model/phone";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {PhoneService} from "../../../service/phone.service";
import {COUNTRIES} from "./countries";
import {Country} from "../../../model/country";

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
  phoneForm: FormGroup | undefined;
  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];
  @Input()
  artOfForm: String | undefined;

  countriesList: Country[] = COUNTRIES;

  constructor(public activeModal: NgbActiveModal,
              private phoneService: PhoneService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.phone)
      this.setFormValue();

    this.phoneForm!.valueChanges.subscribe(value => console.log(this.phoneForm?.controls))
  }

  private initForm() {
    this.phoneForm = this.fb.group({
      'id': [null],
      'countryCode': [null, [Validators.required]],
      'telephoneNumber': [null, [Validators.required, Validators.pattern('^[0-9]+$'), Validators.maxLength(30), Validators.minLength(5)]],
      'isFavorite': [null],
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

    this.errorStatus = undefined;

    if (this.phone) {
      this.editPhone();
    } else {
      this.addPhone();
    }
  }

  private editPhone() {
    const editPhone = this.phoneForm?.value;
    const getEditPhoneSubscribe = this.phoneService.editPhone(editPhone)
      .subscribe(
        value => this.activeModal.close(editPhone)
        ,
        error => this.errorStatus = 'something went wrong with process of editing your phone'
      );
    this.subscriptions.push(getEditPhoneSubscribe);
  }

  private addPhone() {

    const addPhoneSubscribe = this.phoneService.addPhone(this.phoneForm?.value)
      .subscribe(value => this.activeModal.close(value),
        error => this.errorStatus = 'something went wrong with process of adding your phone');

    this.subscriptions.push(addPhoneSubscribe);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }


}
