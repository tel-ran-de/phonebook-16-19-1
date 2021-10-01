import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ContactsComponent} from "./pages/contacts/contact-list/contacts.component";
import {ContactDetailsPageComponent} from "./pages/contact-details-page/contact-details-page.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";

const routes: Routes = [
  {path: '', redirectTo: '/contacts', pathMatch: 'full'},
  {path: 'contacts', component: ContactsComponent, pathMatch: 'full'},
  {path: 'contacts/:id', component: ContactDetailsPageComponent, pathMatch: 'full'},

  {path: 'favorites', component: PageNotFoundComponent},
  {path: 'groups', component: PageNotFoundComponent},
  {path: 'profile', component: PageNotFoundComponent},
  {path: 'logout', component: PageNotFoundComponent},

  {path: '404', component: PageNotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
