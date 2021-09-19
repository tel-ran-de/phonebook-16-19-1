import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  members: { fName: string, lName: string, socialLink: string, info: string }[] = [
    {
      fName: 'Andrej',
      lName: 'Reutow',
      socialLink: 'https://www.linkedin.com/ar1988',
      info: 'Fullstack developer (Java+Angular)'
    },
    {
      fName: 'Andrej',
      lName: 'Reutow',
      socialLink: 'https://www.linkedin.com/ar1988',
      info: 'Fullstack developer (Java+Angular)'
    },
    {
      fName: 'Andrej',
      lName: 'Reutow',
      socialLink: 'https://www.linkedin.com/ar1988',
      info: 'Fullstack developer (Java+Angular)'
    },
    {
      fName: 'Andrej',
      lName: 'Reutow',
      socialLink: 'https://www.linkedin.com/ar1988',
      info: 'Fullstack developer (Java+Angular)'
    },
  ];

  constructor() {
  }

  ngOnInit(): void {
  }
}
