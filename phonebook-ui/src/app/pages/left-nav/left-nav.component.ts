import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditContactFormComponent} from "../modalwindows/add-and-edit-contact-form/add-and-edit-contact-form.component";

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent {
  headerElements: { url: string, label: string, icon: string }[] = [
    {label: 'Contacts', url: '/contacts', icon: 'bi bi-person-lines-fill'},
    {label: 'Favorites', url: '/favorites', icon: 'bi bi-star'},
    {label: 'Groups', url: '/groups', icon: 'bi bi-card-list'},
    {label: 'Profile', url: '/profile', icon: 'bi bi-person-badge'},
    {label: 'Logout', url: '/logout', icon: 'bi bi-box-arrow-right'}
  ];

  sidebarCollapsed = true;

  constructor(private router: Router,
              private modalService: NgbModal) {
  }

  isActive(currentRoute: string): boolean {
    return this.router.isActive(currentRoute, {
      paths: 'exact',
      queryParams: 'subset',
      fragment: 'ignored',
      matrixParams: 'ignored'
    });
  }

  open() {
    const modalRef = this.modalService.open(AddAndEditContactFormComponent);
    modalRef.componentInstance.artOfForm = "Add contact";
  }
}
