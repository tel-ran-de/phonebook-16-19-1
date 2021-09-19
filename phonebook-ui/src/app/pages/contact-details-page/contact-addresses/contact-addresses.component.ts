import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact-addresses',
  templateUrl: './contact-addresses.component.html',
  styleUrls: ['./contact-addresses.component.css']
})
export class ContactAddressesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  open() {
    console.log("address")
  }
}
