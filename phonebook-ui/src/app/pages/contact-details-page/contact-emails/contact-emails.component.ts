import {Component, OnDestroy, OnInit} from '@angular/core';
import {EmailService} from "../../../service/email.service";
import {Email} from "../../../model/email";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-contact-emails',
  templateUrl: './contact-emails.component.html',
  styleUrls: ['./contact-emails.component.css']
})
export class ContactEmailsComponent implements OnInit, OnDestroy {

  emails: Email[] | undefined;
  getAllEmailsErrorMessage: string | undefined;

  private subscriptions: Subscription[] = [];

  constructor(private emailService: EmailService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getAllEmails();
  }

  addEmail() {
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
