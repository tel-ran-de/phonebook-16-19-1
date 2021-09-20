import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ContactsComponent} from "./pages/contacts/contact-list/contacts.component";
import {ContactDetailsPageComponent} from "./pages/contact-details-page/contact-details-page.component";

const routes: Routes = [
  {path: '', redirectTo: '/contacts', pathMatch: 'full'},
  {path: 'contacts', component: ContactsComponent},
  {path: 'contacts/:id', component: ContactDetailsPageComponent},

  {path: 'contacts/favorites', component: ContactDetailsPageComponent},
  {path: 'contacts/groups', component: ContactDetailsPageComponent},
  {path: 'contacts/profile', component: ContactDetailsPageComponent},
  {path: 'logout', component: ContactDetailsPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
