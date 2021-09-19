import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ContactsComponent} from "./pages/contacts/contact-list/contacts.component";
import {ContactDetailsPageComponent} from "./pages/contact-details-page/contact-details-page.component";

const routes: Routes = [
  { path: '', redirectTo: '/contacts', pathMatch: 'full' },
  { path: 'contacts', component: ContactsComponent },
  { path: 'details/:id', component: ContactDetailsPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
