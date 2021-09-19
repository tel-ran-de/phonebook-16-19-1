import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-left-nav-search',
  templateUrl: './left-nav-search.component.html',
  styleUrls: ['./left-nav-search.component.css']
})
export class LeftNavSearchComponent {

  searchTerm: string = '';
  @Output()
  searchSubmitted: EventEmitter<void> = new EventEmitter<void>();

  constructor() {
  }

  onClickSearch() {
    this.searchSubmitted.emit();
    //перенавправить на страницу поиска (host/contacts/search?s=)
    //указать в url в качестве параметра s поисковое значние из формы ввода.
    //пример. В поиск введено "Max" тогда адрес будет host/contacts/search?s=Max
    //
    //компонент, который отображается по адресу выше должен уметь считывать параметры с url, сортировать список контактов,
    //отображать отсортированные результаты

    //выполнять поиск только когда введено миниму 3и символа
    console.log(this.searchTerm);
  }
}
