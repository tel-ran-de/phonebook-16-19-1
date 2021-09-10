import { Injectable } from '@angular/core';
import {InMemoryDbService} from "angular-in-memory-web-api";
import {Contact} from "./model/contact";


@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const contacts : Contact[] = [
      { id: 11, firstName: 'Dr Nice', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home' },
      { id: 12, firstName: 'Narco', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home'  },
      { id: 13, firstName: 'Bombasto', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home'  },
      { id: 14, firstName: 'Celeritas', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home' },
      { id: 15, firstName: 'Magneta', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home'  },
      { id: 16, firstName: 'RubberMan', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home' },
      { id: 17, firstName: 'Dynama', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home' },
      { id: 18, firstName: 'Dr IQ', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home'  },
      { id: 19, firstName: 'Magma', lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home'  },
      { id: 20, firstName: 'Tornado' , lastName: 'Pupkin', age: 20, isFavorite: true, group: 'home' }
    ];
    return {contacts};
  }

  genId(contacts: Contact[]): number {
    return contacts.length > 0 ? Math.max(...contacts.map(contact => contact.id)) + 1 : 11;
  }
}
