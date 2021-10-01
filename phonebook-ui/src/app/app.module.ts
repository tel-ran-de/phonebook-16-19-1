import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';


import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {ContactsComponent} from './pages/contacts/contact-list/contacts.component';
import {CardComponent} from './pages/contacts/contact-item/card.component';
import {ContactDetailsPageComponent} from './pages/contact-details-page/contact-details-page.component';
import {ContactDetailComponent} from './pages/contact-details-page/contact-detail/contact-detail.component';
import {ContactEmailsComponent} from './pages/contact-details-page/contact-emails/contact-emails.component';
import {ContactPhonesComponent} from './pages/contact-details-page/contact-phones/contact-phones.component';
import {ContactAddressesComponent} from './pages/contact-details-page/contact-addresses/contact-addresses.component';
import {HeaderComponent} from "./pages/header/header.component";
import {LeftNavComponent} from "./pages/left-nav/left-nav.component";
import {LeftNavSearchComponent} from "./pages/left-nav/left-nav-search/left-nav-search.component";
import {FooterComponent} from "./pages/footer/footer.component";
import {AddAndEditContactFormComponent} from './pages/modalwindows/add-and-edit-contact-form/add-and-edit-contact-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PhoneDetailsComponent} from "./pages/contact-details-page/contact-phones/phone-details/phone-details.component";
import {EmailElementComponent} from "./pages/contact-details-page/contact-emails/email-element/email-element.component";
import {SortByFavoritePipe} from "./pages/contact-details-page/sort-by-favorite.pipe";
import {AddressDetailsComponent} from './pages/contact-details-page/contact-addresses/address-details/address-details.component';
import {AddAndEditAddressFormComponent} from './pages/modalwindows/add-and-edit-address-form/add-and-edit-address-form.component';
import {ToastsContainer} from './pages/toasts/toasts.component';
import {AddAndEditEmailFormComponent} from './pages/modalwindows/add-and-edit-email-form/add-and-edit-email-form.component';
import {PageNotFoundComponent} from './pages/page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    ContactsComponent,
    CardComponent,
    ContactDetailsPageComponent,
    ContactDetailComponent,
    ContactEmailsComponent,
    ContactPhonesComponent,
    HeaderComponent,
    LeftNavComponent,
    LeftNavSearchComponent,
    FooterComponent,
    ContactAddressesComponent,
    AddAndEditContactFormComponent,
    EmailElementComponent,
    SortByFavoritePipe,
    PhoneDetailsComponent,
    AddressDetailsComponent,
    AddAndEditEmailFormComponent,
    AddAndEditAddressFormComponent,
    ToastsContainer,
    PageNotFoundComponent
  ],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
