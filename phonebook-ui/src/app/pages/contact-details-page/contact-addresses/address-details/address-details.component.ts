import {Component, EventEmitter, Input, Output,} from '@angular/core';
import {Address} from "../../../../model/address";
import {Subscription} from 'rxjs';
import {AddressService} from 'src/app/service/address.serivce';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AddAndEditAddressFormComponent} from "../../../modalwindows/add-and-edit-address-form/add-and-edit-address-form.component";
import {ToastService} from "../../../../service/toast.service";
import {httpErrorHandler} from "../../../../httpErrorHandle";


@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent {

  @Input()
  address: Address | undefined;
  @Output()
  addressDeleted: EventEmitter<Address> = new EventEmitter<Address>();

  private subscriptions: Subscription[] = [];

  constructor(private modalService: NgbModal,
              private addressService: AddressService,
              private toastService: ToastService) {
  }

  openEditAddressModal(): void {
    const modalRef = this.modalService.open(AddAndEditAddressFormComponent);

    modalRef.componentInstance.address = this.address;
    modalRef.componentInstance.artOfForm = "Edit your address"
    modalRef.closed.subscribe(value => this.address = value);
  }

  onClickDelete(): void {
    const deleteAddressSubscribe = this.addressService.delete(this.address!.id)
      .subscribe(_ => this.addressDeleted.emit(this.address),
        error => this.toastService.show(httpErrorHandler(error),
          {
            classname: 'bg-danger text-light mt-5',
            delay: 5000
          })
      );

    this.subscriptions.push(deleteAddressSubscribe)
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
