import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {httpErrorHandler} from "../../../httpErrorHandle";

@Component({
  selector: 'app-add-and-edit-contact-form',
  templateUrl: './add-and-edit-contact-form.component.html',
  styleUrls: ['./add-and-edit-contact-form.component.css']
})
export class AddAndEditContactFormComponent implements OnInit, OnDestroy {

  @Input()
  contact: Contact | undefined;
  @Input()
  artOfForm: String | undefined;

  profileForm: FormGroup | undefined;
  errorMessage: String | undefined;
  private subscriptions: Subscription[] = [];

  constructor(public activeModal: NgbActiveModal,
              private contactService: ContactService,
              private router: Router,
              private fb: FormBuilder,) {
  }

  ngOnInit(): void {
    this.initForm();

    if (this.contact)
      this.profileForm?.setValue(this.contact);
  }

  onSubmit(): void {

    this.errorMessage = undefined;

    if (this.contact)
      this.editContact();
    else
      this.addContact();
  }

  private initForm(): void {
    this.profileForm = this.fb.group({
      'id': [null],
      'firstName': [null, [Validators.required]],
      'lastName': [null, [Validators.required]],
      'age': [18, [Validators.required, Validators.min(1), Validators.max(120)]],
      'isFavorite': [null],
      'group': ["home"]
    });
  }

  private editContact(): void {

    const editContact = this.profileForm?.value;

    const getEditContactSubscribe = this.contactService.edit(editContact)
      .subscribe(_ => this.activeModal.close(editContact),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getEditContactSubscribe);
  }

  private addContact(): void {

    const getAddContactSubscribe = this.contactService.add(this.profileForm?.value)
      .subscribe(value => this.callBackOk(value),
        error => this.errorMessage = httpErrorHandler(error));

    this.subscriptions.push(getAddContactSubscribe);
  }

  private callBackOk(value: Contact): void {

    const url = `contacts/${value.id}`;

    this.activeModal.close();
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([url]));
  }

  ngOnDestroy(): void {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
