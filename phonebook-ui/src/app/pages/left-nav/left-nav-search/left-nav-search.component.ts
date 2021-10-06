import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-left-nav-search',
  templateUrl: './left-nav-search.component.html',
  styleUrls: ['./left-nav-search.component.css']
})
export class LeftNavSearchComponent implements OnInit{

  searchForm: FormGroup | undefined;

  constructor(private router: Router, private fb: FormBuilder) {
  }

  ngOnInit(): void {

    this.initForm();
  }

  onClickSearch() {

    const param = this.searchForm?.value.searchContact;
    this.searchForm?.controls.searchContact.setValue('');
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate(['search'],  { queryParams: { searchContact: param } }));
  }

  private initForm(): void {

    this.searchForm = this.fb.group({
      'searchContact': [ null, [Validators.minLength(3)]]
    });
  }
}
