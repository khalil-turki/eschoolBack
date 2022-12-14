import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {ColumnMode, DatatableComponent} from '@swimlane/ngx-datatable';

import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

import {CoreConfigService} from '@core/services/config.service';
import {CoreSidebarService} from '@core/components/core-sidebar/core-sidebar.service';

import {UserListService} from 'app/main/apps/user/user-list/user-list.service';
import {User} from "../../../../auth/models";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class UserListComponent implements OnInit {
  public rows: User[];
  public selectedOption = 10;
  public ColumnMode = ColumnMode;
  public temp = [];
  public previousRoleFilter = '';
  public previousStatusFilter = undefined;

  public selectRole: any = [
    {name: 'All', value: ''},
    {name: 'Admin', value: 'ROLE_ADMIN'},
    {name: 'Etudiant', value: 'ROLE_ETUDIANT'},
    {name: 'Professeur', value: 'ROLE_PROFESSEUR'},
    {name: 'Parent', value: 'ROLE_PARENT'}
  ];

  public selectStatus: any = [
    {name: 'All', value: undefined},
    {name: 'Enabled', value: true},
    {name: 'Disabled', value: false}
  ];

  public selectedRole = [];
  public selectedStatus = undefined;
  public searchValue = '';

  @ViewChild(DatatableComponent) table: DatatableComponent;

  private tempData = [];
  private _unsubscribeAll: Subject<any>;

  constructor(
      private _userListService: UserListService,
      private _coreSidebarService: CoreSidebarService,
      private _coreConfigService: CoreConfigService
  ) {
    this._unsubscribeAll = new Subject();
  }

  filterUpdate(event) {
    this.selectedRole = this.selectRole[0];
    this.selectedStatus = this.selectStatus[0];

    const val = event.target.value.toLowerCase();

    const temp = this.tempData.filter(function (d) {
      return (d.nom + ' ' + d.prenom).toLowerCase().indexOf(val) !== -1 || !val;
    });

    this.rows = temp;
    this.table.offset = 0;
  }

  toggleSidebar(name): void {
    this._coreSidebarService.getSidebarRegistry(name).toggleOpen();
  }

  filterByRole(event) {
    const filter = event ? event.value : '';
    this.previousRoleFilter = filter;
    this.temp = this.filterRows(filter, this.previousStatusFilter);
    this.rows = this.temp;
  }

  filterByStatus(event) {
    const filter = event ? event.value : '';
    this.previousStatusFilter = filter;
    this.temp = this.filterRows(this.previousRoleFilter, filter);
    this.rows = this.temp;
  }

  filterRows(roleFilter, statusFilter): any[] {
    roleFilter = roleFilter.toLowerCase();

    return this.tempData.filter(row => {
      const isPartialRoleMatch = row.role.toLowerCase().indexOf(roleFilter) !== -1 || !roleFilter;
      const isPartialStatusMatch = statusFilter === undefined || row.enabled === statusFilter;
      const isPartialNameMatch = (row.nom + ' ' + row.prenom).toLowerCase().indexOf(this.searchValue.toLowerCase()) !== -1 || !this.searchValue;
      return isPartialRoleMatch && isPartialStatusMatch && isPartialNameMatch;
    });
  }

  ngOnInit(): void {
    this._coreConfigService.config.pipe(takeUntil(this._unsubscribeAll)).subscribe(config => {
      if (config.layout.animation === 'zoomIn') {
        setTimeout(() => {
          this._userListService.onUserListChanged.pipe(takeUntil(this._unsubscribeAll)).subscribe(response => {
            this.rows = response;
            this.tempData = this.rows;
          });
        }, 450);
      } else {
        this._userListService.onUserListChanged.pipe(takeUntil(this._unsubscribeAll)).subscribe(response => {
          this.rows = response;
          this.tempData = this.rows;
        });
      }
    });
  }

  ngOnDestroy(): void {
    this._unsubscribeAll.next();
    this._unsubscribeAll.complete();
  }
}
