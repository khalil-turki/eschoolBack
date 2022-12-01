import {Component, OnDestroy, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CoreSidebarService } from '@core/components/core-sidebar/core-sidebar.service';
import { InvoicePreviewService } from 'app/main/apps/invoice/invoice-preview/invoice-preview.service';
import {InvoiceService} from "./invoice.service";
import Swal from "sweetalert2";
import {PayementControllerService} from "../../../../gs-api/src/services/payement-controller.service";



@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.scss']
})

export class InvoiceComponent implements OnInit, OnDestroy {
  // public
  public apiData;
  public urlLastValue;
  public url = this.router.url;
  public paymentDetails = {
    totalDue: '7850 TND',
    paymentVia: 'stripe',
    country: 'Tunisia',
    iban: 'ETD95476213874685',
    swiftCode: 'BR91905'
  };

  // private
  private _unsubscribeAll: Subject<any>;

  /**
   * Constructor
   *
   * @param {Router} router
   * @param {InvoiceService} _invoicePreviewService
   * @param {CoreSidebarService} _coreSidebarService
   */
  constructor(
      private router: Router,
      private _invoicePreviewService: InvoiceService,
      private _coreSidebarService: CoreSidebarService,
      private payementService: PayementControllerService,
  ) {
    this._unsubscribeAll = new Subject();
    this.urlLastValue = this.url.substr(this.url.lastIndexOf('/') + 1);
  }

  // Public Methods
  // -----------------------------------------------------------------------------------------------------

  /**
   * Toggle the sidebar
   *
   * @param name
   */
  toggleSidebar(name): void {
    this._coreSidebarService.getSidebarRegistry(name).toggleOpen();
  }

  // Lifecycle Hooks
  // -----------------------------------------------------------------------------------------------------
  /**
   * On init
   */
  ngOnInit(): void {

  }

  /**
   * On destroy
   */
  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions
    this._unsubscribeAll.next();
    this._unsubscribeAll.complete();
  }

  CreateInvoicePaymentRequest(){
    return new Promise((resolve, reject) => {
      Swal.fire({
        title: 'Please Wait',
        html: 'Creating payment session...',// add html attribute if you want or remove
        allowOutsideClick: false,
        onBeforeOpen: () => {
          Swal.showLoading()
        },
      });
      this.payementService.payUsingGET().subscribe((response: any) => {
        console.log((response));
        Swal.close();
        resolve(this.apiData);
        window.location = response.checkoutURI;
      }, message => {
        Swal.close();
        Swal.fire({
          icon: 'error',
          title: 'Failed!',
          text: 'Could not create payment session. ' + message,
          customClass: {
            confirmButton: 'btn btn-danger'
          }
        });
        reject();
      });
    });
  }



}
