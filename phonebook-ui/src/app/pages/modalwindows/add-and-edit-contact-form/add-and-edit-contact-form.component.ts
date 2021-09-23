import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Contact} from "../../../model/contact";
import {ContactService} from "../../../service/contact.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-and-edit-contact-form',
  templateUrl: './add-and-edit-contact-form.component.html',
  styleUrls: ['./add-and-edit-contact-form.component.css']
})
export class AddAndEditContactFormComponent implements OnInit, OnDestroy {


  @Input()
  contact : Contact | undefined;
  profileForm: FormGroup | undefined;
  errorStatus : String |  undefined;
  subscriptions: Subscription[] = [];
  @Input()
  artOfForm: String | undefined;

  constructor(public activeModal: NgbActiveModal,
              private contactService: ContactService,
              private router: Router,
              private fb: FormBuilder,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {

    this.initForm();
    if(this.contact)
      this.setFormValues();
  }

  onSubmit(){

    this.errorStatus = undefined;

    if(this.artOfForm === "Edit your contact")
    {
      this.editContact();
      return;
    }

    const getAddContactSubscribe = this.contactService.addContact(this.profileForm?.value)
      .subscribe(value => {
        this.callBackOkk(value)
      },
        error => {
          this.errorStatus = 'something went wrong with process of adding your contacts';
        });

    this.subscriptions.push(getAddContactSubscribe);

  }

  private callBackOkk(value: Contact) {

    this.redirect(value);
    this.activeModal.close();
  }

  private redirect(value: Contact) {

    const url = `contacts/${value.id}`;
    this.router.navigateByUrl(url);

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
      'firstName': [null, Validators.required],
      'lastName': [null, Validators.required],
      'age': [null, Validators.required],
      'isFavorite': [null],
      'group': [null]
    });
  }

  private editContact() {

    this.activeModal.close();

    const getEditContactSubscribe = this.contactService.editContact(this.profileForm?.value)
      .subscribe(
        error => {
          this.errorStatus = 'something went wrong with process of edit your contacts';
        });

    this.subscriptions.push(getEditContactSubscribe);

    const url = `contacts`
    this.router.navigateByUrl(url);
    // this.router.navigate([`../${this.profileForm?.value.id}`], { relativeTo: this.route });

  }

  ngOnDestroy(){

    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
