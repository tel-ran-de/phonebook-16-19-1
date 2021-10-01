import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {EmailService} from "../../../service/email.service";
import {Email} from "../../../model/email";
import {httpErrorHandler} from "../../../httpErrorHandle";

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
  @Input()
  artOfForm: String | undefined;

  emailForm: FormGroup | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(public activeModal: NgbActiveModal,
              private emailService: EmailService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {

    this.initForm();
    if (this.email)
      this.emailForm!.setValue(this.email)
  }

  private initForm(): void {

    this.emailForm = this.fb.group({
      'id': [null],
      'email': [null, [Validators.required, Validators.pattern('[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$')]],
      'isFavorite': [null],
      'contactId': [this.contactId],
    });
  }

  onSubmit(): void {

    this.errorMessage = undefined;

    if (this.email)
      this.editAddress();
    else
      this.addAddress();
  }

  private editAddress(): void {

    const editEmail = this.emailForm?.value;

    const getEditEmailSubscribe = this.emailService.edit(editEmail)
      .subscribe(_ => this.activeModal.close(editEmail),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getEditEmailSubscribe);
  }

  private addAddress(): void {

    const getAddEmailSubscribe = this.emailService.add(this.emailForm?.value)
      .subscribe(value => this.activeModal.close(value),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getAddEmailSubscribe);
  }

  ngOnDestroy(): void {

    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
