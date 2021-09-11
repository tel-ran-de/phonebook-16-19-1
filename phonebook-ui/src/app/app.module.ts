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
import { ContactDetailsComponent } from './pages/contact-details/contact-details.component';


@NgModule({
  declarations: [
    AppComponent,
    ContactsComponent,
    CardComponent,
    ContactDetailsComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
