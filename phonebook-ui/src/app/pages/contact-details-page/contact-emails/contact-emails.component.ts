import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact-emails',
  templateUrl: './contact-emails.component.html',
  styleUrls: ['./contact-emails.component.css']
})
export class ContactEmailsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  open() {
    console.log("emails")
  }

}
