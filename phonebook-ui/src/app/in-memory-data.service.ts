import { Injectable } from '@angular/core';
import {InMemoryDbService} from "angular-in-memory-web-api";
import {Contact} from "./model/contact";


@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const contacts : Contact[] = [
      { id: 11, firstName: 'Falco', lastName: 'Bergener', age: 27, isFavorite: true, group: 'home' },
      { id: 12, firstName: 'Maik', lastName: 'Pupkin', age: 30, isFavorite: false, group: 'private' },
      { id: 13, firstName: 'Cristina', lastName: 'Rohder', age: 20, isFavorite: true, group: 'other'  },
      { id: 14, firstName: 'Roman', lastName: 'Bergman', age: 40, isFavorite: false, group: 'private' },
      { id: 15, firstName: 'Cristopfer', lastName: 'Muller', age: 52, isFavorite: true, group: 'home' },

    ];
    return {contacts};
  }

  genId(contacts: Contact[]): number {
    return contacts.length > 0 ? Math.max(...contacts.map(contact => contact.id)) + 1 : 11;
  }
}
