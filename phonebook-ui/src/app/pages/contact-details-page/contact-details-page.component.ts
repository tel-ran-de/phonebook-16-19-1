import {Component, Input, OnInit} from '@angular/core';
import {Contact} from "../../model/contact";
import {ActivatedRoute} from "@angular/router";
import {ContactService} from "../../service/contact.service";

@Component({
  selector: 'app-contact-details-page',
  templateUrl: './contact-details-page.component.html',
  styleUrls: ['./contact-details-page.component.css']
})
export class ContactDetailsPageComponent implements OnInit {

  @Input()
  contact: Contact | undefined;

  constructor(private route: ActivatedRoute,
              private contactService: ContactService) { }

  ngOnInit(): void {
    this.getContact();
  }

  private getContact() : void{
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.contactService.getContact(id)
      .subscribe(contact => this.contact = contact);
  }

}
