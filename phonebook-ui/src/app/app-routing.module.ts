import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ContactsComponent} from "./pages/contacts/contact-list/contacts.component";
import {ContactDetailsComponent} from "./pages/contact-details/contact-details.component";

const routes: Routes = [
  { path: '', redirectTo: '/contacts', pathMatch: 'full' },
  { path: 'contacts', component: ContactsComponent },
  { path: 'details/:id', component: ContactDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
