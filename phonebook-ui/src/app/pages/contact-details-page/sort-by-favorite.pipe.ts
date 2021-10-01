import {Pipe, PipeTransform} from '@angular/core';
import {Contact} from "../../model/contact";
import {Phone} from "../../model/phone";
import {Address} from "../../model/address";
import {Email} from "../../model/email";

@Pipe({
  name: 'sortByFavorite',
  pure: false
})
export class SortByFavoritePipe implements PipeTransform {

  transform(value: Contact[] | Phone[] | Address[] | Email[]): any {
    if (!value)
      return [];

    return value.sort(a => a.isFavorite ? -1 : 1);
  }
}
