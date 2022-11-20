import {Component, OnDestroy, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CoreSidebarService } from '@core/components/core-sidebar/core-sidebar.service';
import { InvoicePreviewService } from 'app/main/apps/invoice/invoice-preview/invoice-preview.service';
import {InvoiceService} from "./invoice.service";



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
      private _coreSidebarService: CoreSidebarService
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

}
