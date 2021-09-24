import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-and-edit-contact-form',
  templateUrl: './add-and-edit-contact-form.component.html',
  styleUrls: ['./add-and-edit-contact-form.component.css']
})
export class AddAndEditContactFormComponent implements OnInit, OnDestroy {

  @Input()
  contact: Contact | undefined;
  profileForm: FormGroup | undefined;
  errorStatus: String | undefined;
  subscriptions: Subscription[] = [];
  @Input()
  artOfForm: String | undefined;

  constructor(public activeModal: NgbActiveModal,
              private contactService: ContactService,
              private router: Router,
              private fb: FormBuilder,) {
  }

  ngOnInit(): void {

    this.initForm();
    if (this.contact)
      this.setFormValues();
  }

  onSubmit() {

    this.errorStatus = undefined;

    if (this.artOfForm === "Edit your contact") {
      this.editContact();
    } else {
      this.addContact();
    }
  }

  private setFormValues() {

    this.profileForm?.controls.firstName.setValue(this.contact?.firstName);
    this.profileForm?.controls.lastName.setValue(this.contact?.lastName);
    this.profileForm?.controls.age.setValue(this.contact?.age);
    this.profileForm?.controls.id.setValue(this.contact?.id);
    this.profileForm?.controls.isFavorite.setValue(this.contact?.isFavorite);
    this.profileForm?.controls.group.setValue(this.contact?.group);
  }

  private initForm() {

    this.profileForm = this.fb.group({
      'id': [null],
      'firstName': [null, [Validators.required]],
      'lastName': [null, [Validators.required]],
      'age': [1, [Validators.required, Validators.min(1), Validators.max(120)]],
      'isFavorite': [null],
      'group': ["home"]
    });
  }

  private editContact() {

    const editContact = this.profileForm?.value;

    const getEditContactSubscribe = this.contactService.editContact(editContact)
      .subscribe(
        value => {
          this.activeModal.close(editContact);
        },
        error => {
          this.errorStatus = 'something went wrong with process of edit your contacts';
        });

    this.subscriptions.push(getEditContactSubscribe);
  }

  private addContact() {

    const getAddContactSubscribe = this.contactService.addContact(this.profileForm?.value)
      .subscribe(value => {
          this.callBackOk(value)
        },
        error => {
          this.errorStatus = 'something went wrong with process of adding your contacts';
        });

    this.subscriptions.push(getAddContactSubscribe);
  }

  private callBackOk(value: Contact) {

    const url = `contacts/${value.id}`;

    this.activeModal.close();
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([url]));
  }

  ngOnDestroy() {

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
