import {Component, OnDestroy, OnInit} from '@angular/core';
import {EmailService} from "../../../service/email.service";
import {Email} from "../../../model/email";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {Subscription} from "rxjs";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditEmailFormComponent} from "../../modalwindows/add-and-edit-email-form/add-and-edit-email-form.component";
import {ToastService} from 'src/app/service/toast.service';

@Component({
  selector: 'app-contact-emails',
  templateUrl: './contact-emails.component.html',
  styleUrls: ['./contact-emails.component.css']
})
export class ContactEmailsComponent implements OnInit, OnDestroy {

  emails: Email[] | undefined;
  getAllEmailsErrorMessage: string | undefined;

  private subscriptions: Subscription[] = [];

  constructor(private emailService: EmailService,
              private route: ActivatedRoute,
              private modalService: NgbModal,
              private toastService: ToastService) {
  }

  ngOnInit(): void {
    this.getAllEmails();
  }

  addEmail() {
    const contactId: number = Number(this.route.snapshot.paramMap.get('id'));

    const modalRef = this.modalService.open(AddAndEditEmailFormComponent);
    modalRef.componentInstance.artOfForm = "Add your email";
    modalRef.componentInstance.contactId = contactId;
    modalRef.closed.subscribe(value => this.emails?.push(value));
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

  deleteEmail($event: Email) {

    const deleteEmailSubscription = this.emailService.deleteEmail($event.id)
      .subscribe(_ => this.emails = this.emails!.filter(h => h !== $event),
        () => this.toastService.show('something went wrong with deleting email',
          {
            classname: 'bg-danger text-light mt-5',
            delay: 5000
          })
      );

    this.subscriptions.push(deleteEmailSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }
}
