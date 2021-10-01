import {Component, OnDestroy, OnInit} from '@angular/core';
import {EmailService} from "../../../service/email.service";
import {Email} from "../../../model/email";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditEmailFormComponent} from "../../modalwindows/add-and-edit-email-form/add-and-edit-email-form.component";
import {ToastService} from 'src/app/service/toast.service';
import {httpErrorHandler} from "../../../httpErrorHandle";

@Component({
  selector: 'app-contact-emails',
  templateUrl: './contact-emails.component.html',
  styleUrls: ['./contact-emails.component.css']
})
export class ContactEmailsComponent implements OnInit, OnDestroy {

  emails: Email[] | undefined;
  getAllEmailsErrorMessage: string | undefined;

  private subscriptions: Subscription[] = [];
  private contactId!: number;

  constructor(private emailService: EmailService,
              private route: ActivatedRoute,
              private modalService: NgbModal,
              private toastService: ToastService) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
    this.getAllEmails();
  }

  addEmail(): void {
    const modalRef = this.modalService.open(AddAndEditEmailFormComponent);

    modalRef.componentInstance.artOfForm = "Add your email";
    modalRef.componentInstance.contactId = this.contactId;

    modalRef.closed.subscribe(value => this.emails?.push(value));
  }

  private getAllEmails(): void {
    this.getAllEmailsErrorMessage = undefined;

    const getAllEmailsSubscription = this.emailService.getAll(this.contactId)
      .subscribe(value => this.emails = value,
        error => httpErrorHandler(error));

    this.subscriptions.push(getAllEmailsSubscription);
  }

  deleteEmail($event: Email): void {

    const deleteEmailSubscription = this.emailService.delete($event.id)
      .subscribe(_ => this.emails = this.emails!.filter(h => h !== $event),
        error => this.toastService.show(httpErrorHandler(error),
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
