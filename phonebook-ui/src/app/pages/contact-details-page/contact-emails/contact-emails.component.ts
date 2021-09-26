import {Component, OnDestroy, OnInit} from '@angular/core';
import {EmailService} from "../../../service/email.service";
import {Email} from "../../../model/email";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {Subscription} from "rxjs";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditEmailFormComponent} from "../../modalwindows/add-and-edit-email-form/add-and-edit-email-form.component";

@Component({
  selector: 'app-contact-emails',
  templateUrl: './contact-emails.component.html',
  styleUrls: ['./contact-emails.component.css']
})
export class ContactEmailsComponent implements OnInit, OnDestroy {

  emails: Email[] | undefined;
  getAllEmailsErrorMessage: string | undefined;

  private subscriptions: Subscription[] = [];

  constructor(private emailService: EmailService, private route: ActivatedRoute,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.getAllEmails();
  }

  addEmail() {

    const contactId: number = Number(this.route.snapshot.paramMap.get('id'));

    const modalRef = this.modalService.open(AddAndEditEmailFormComponent);
    modalRef.componentInstance.artOfForm = "Add your email";
    modalRef.componentInstance.contactId = contactId;
  }

  private getAllEmails() {
    this.getAllEmailsErrorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get('id'));

    const getAllEmailsSubscription = this.emailService.getAll(contactId)
      .subscribe(value => this.emails = value,
        error => this.callBackGetAllEmailError(error))

    this.subscriptions.push(getAllEmailsSubscription);
  }

  private callBackGetAllEmailError(error: HttpErrorResponse) {
    this.getAllEmailsErrorMessage = "Error";
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }
}
