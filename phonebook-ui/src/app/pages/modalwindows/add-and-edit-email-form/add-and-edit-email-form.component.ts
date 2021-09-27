import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {EmailService} from "../../../service/email.service";
import {Email} from "../../../model/email";

@Component({
  selector: 'app-add-and-edit-email-form',
  templateUrl: './add-and-edit-email-form.component.html',
  styleUrls: ['./add-and-edit-email-form.component.css']
})
export class AddAndEditEmailFormComponent implements OnInit, OnDestroy {

  @Input()
  email: Email | undefined;
  @Input()
  contactId: number | undefined;
  emailForm: FormGroup | undefined;
  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];
  @Input()
  artOfForm: String | undefined;

  constructor(public activeModal: NgbActiveModal, private emailService: EmailService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {

    this.initForm();
    if (this.email)
      this.setFormValue();
  }

  private initForm() {

    this.emailForm = this.fb.group({
      'id': [null],
      'email': [null, [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$')]],
      'isFavorite': [null],
      'contactId': [this.contactId],
    });
  }

  private setFormValue() {

    this.emailForm!.controls.email.setValue(this.email!.email);
    this.emailForm!.controls.id.setValue(this.email!.id);
    this.emailForm!.controls.isFavorite.setValue(this.email!.isFavorite);
    //alternative way to set form with values
    this.emailForm!.setValue(this.email!)
  }

  onSubmit() {

    this.errorStatus = undefined;

    if (this.artOfForm === "Edit your email") {
      this.editAddress();
    } else {
      this.addAddress();
    }
  }

  private editAddress() {

    const editEmail = this.emailForm?.value;

    const getEditEmailSubscribe = this.emailService.editEmail(editEmail)
      .subscribe(
        value =>
          this.activeModal.close(editEmail)
        ,
        error =>
          this.errorStatus = 'something went wrong with process of editing your email'
        );

    this.subscriptions.push(getEditEmailSubscribe);
  }

  private addAddress() {

    const getAddEmailSubscribe = this.emailService.addEmail(this.emailForm?.value)
      .subscribe(value =>
          this.activeModal.close(value)
        ,
        error =>
          this.errorStatus = 'something went wrong with process of adding your email'
        );

    this.subscriptions.push(getAddEmailSubscribe);
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
