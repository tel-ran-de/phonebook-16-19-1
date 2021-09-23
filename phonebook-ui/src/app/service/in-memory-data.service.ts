import {Injectable} from '@angular/core';
import {InMemoryDbService} from "angular-in-memory-web-api";
import {Contact} from "../model/contact";
import {Email} from "../model/email";
import {Phone} from "../model/phone";
import {Address} from "../model/address";


@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const contacts: Contact[] = [
      {id: 11, firstName: 'Falco', lastName: 'Bergener', age: 27, isFavorite: true, group: 'home'},
      {id: 12, firstName: 'Maik', lastName: 'Pupkin', age: 30, isFavorite: false, group: 'private'},
      {id: 13, firstName: 'Cristina', lastName: 'Rohder', age: 20, isFavorite: true, group: 'other'},
      {id: 14, firstName: 'Roman', lastName: 'Bergman', age: 40, isFavorite: false, group: 'private'},
      {id: 15, firstName: 'Cristopfer', lastName: 'Muller', age: 52, isFavorite: true, group: 'home'},

    ];
    const email: Email[] = this.generateEmail(contacts);
    const phone: Phone[] = this.generatePhone(contacts);
    const address: Address[] = this.generateAddress(contacts);
    return {contacts, email, phone, address};
  }

  genId(contacts: Contact[] | Email[] | Phone[] | Address[]): number {
    return contacts.length > 0 ? Math.max(...contacts.map(contact => contact.id)) + 1 : 11;
  }

  private generateEmail(contacts: Contact[]) {
    const emailArr: Email[] = [];
    let id = 1;
    for (let contactElement of contacts) {
      for (let i = 1; i < 6; i++) {
        emailArr.push(
          {
            id: id,
            email: `${contactElement.firstName}_${contactElement.lastName}_${i}@email.com`,
            isFavorite: i % 3 === 0,
            contactId: contactElement.id
          }
        );
        id++;
      }
    }

    return emailArr;
  }

  private generatePhone(contacts: Contact[]) {
    const phoneArr: Phone[] = [];
    let id = 1;
    for (let contactElement of contacts) {
      for (let i = 1; i < 6; i++) {
        phoneArr.push(
          {
            id: id,
            countryCode: "00" + i,
            telephoneNumber: "0176999000" + id + i + contactElement.id,
            isFavorite: i % 3 === 0,
            contactId: contactElement.id
          }
        );
        id++;
      }
    }

    return phoneArr;
  }

  private generateAddress(contacts: Contact[]) {
    const addresseArr: Address[] = [];
    let id = 1;
    for (let contactElement of contacts) {
      for (let i = 1; i < 6; i++) {
        addresseArr.push(
          {
            id: id,
            city: "City_" + i,
            country: 'Germany',
            homeNr: id,
            index: '1' + i + contactElement.id + id + id * 3,
            street: "main street " + id,
            isFavorite: i % 3 === 0,
            contactId: contactElement.id
          }
        );
        id++;
      }
    }

    return addresseArr;
  }
}
