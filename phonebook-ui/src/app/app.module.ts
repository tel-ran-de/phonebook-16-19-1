import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './service/in-memory-data.service';
import { ContactsComponent } from './pages/contacts/contact-list/contacts.component';
import { CardComponent } from './pages/contacts/contact-item/card.component';
import { ContactDetailsPageComponent } from './pages/contact-details-page/contact-details-page.component';
import { ContactDetailComponent } from './pages/contact-details-page/contact-detail/contact-detail.component';
import { ContactEmailsComponent } from './pages/contact-details-page/contact-emails/contact-emails.component';
import { ContactPhonesComponent } from './pages/contact-details-page/contact-phones/contact-phones.component';
import { ContactAddressesComponent } from './pages/contact-details-page/contact-addresses/contact-addresses.component';
import { AddAndEditContactFormComponent } from './pages/modalwindows/add-and-edit-contact-form/add-and-edit-contact-form.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    ContactsComponent,
    CardComponent,
    ContactDetailsPageComponent,
    ContactDetailComponent,
    ContactEmailsComponent,
    ContactPhonesComponent,
    ContactAddressesComponent,
    AddAndEditContactFormComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, {dataEncapsulation: false}
    ),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
