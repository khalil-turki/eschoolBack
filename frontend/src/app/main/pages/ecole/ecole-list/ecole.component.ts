import {Component, Injectable, Input, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {EcoleControllerService} from "gs-api/src/services/ecole-controller.service";
import {DatatableComponent, ColumnMode} from "@swimlane/ngx-datatable";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {CoreSidebarService} from "@core/components/core-sidebar/core-sidebar.service";
import {takeUntil} from "rxjs/operators";
import {ClasseDto} from "../../../../../gs-api/src/models/classe-dto";
import {ClasseControllerService} from "../../../../../gs-api/src/services/classe-controller.service";
import {EcoleDto} from "../../../../../gs-api/src/models/ecole-dto";
import {DatatablesService} from "../../../tables/datatables/datatables.service";
import {ChartOptions} from "../../../ui/card/card-analytics/card-analytics.component";

@Injectable({
    providedIn: 'root'
})
@Component({
    selector: 'app-ecole',
    templateUrl: './ecole.component.html',
    styleUrls: ['./ecole.component.scss'],
})
export class EcoleComponent implements OnInit, Resolve<any> {
    @ViewChild('tableRowDetails') tableRowDetails: any;

    @ViewChild(DatatableComponent) table: DatatableComponent;
    public pageBasic = 4;

    public ColumnMode = ColumnMode;
    @Input() ecoleDto: EcoleDto = {};
    public rows: Array<EcoleDto> = [];
    public onDatatablessChanged: BehaviorSubject<any>;
    public isMenuToggled = false;
    public orderChartoptions: Partial<ChartOptions>;
    public data: any;
    public earningChartoptions;

    private _unsubscribeAll: Subject<any>;
    private tempData = [];
    public exportCSVData;
    public kitchenSinkRows: any;
    public pageBasicText = 1;
    public previousRoleFilter = '';
    public previousPlanFilter = '';
    public previousStatusFilter = '';
    public temp = [];
    public searchValue = '';


    public selectStatus: any = [
        { name: 'All', value: '' },
        { name: 'pending', value: 'pending' },
        { name: 'Active', value: 'Active' },
        { name: 'Inactive', value: 'Inactive' }
    ];
    public selectedStatus = [];

    public selectedRole = [];
    public selectedPlan = [];



    /**
     * Filter By Status
     *
     * @param event
     */
    filterByStatus(event) {
        const filter = event ? event.value : '';
        this.previousStatusFilter = filter;
        this.temp = this.filterRows(this.previousRoleFilter, this.previousPlanFilter, filter);
        this.rows = this.temp;
    }
    /**
     * Filter Rows
     *
     * @param roleFilter
     * @param planFilter
     * @param statusFilter
     */
    filterRows(roleFilter, planFilter, statusFilter): any[] {
        // Reset search on select change
        this.searchValue = '';

        roleFilter = roleFilter.toLowerCase();
        planFilter = planFilter.toLowerCase();
        statusFilter = statusFilter.toLowerCase();

        return this.tempData.filter(row => {
            const isPartialNameMatch = row.role.toLowerCase().indexOf(roleFilter) !== -1 || !roleFilter;
            const isPartialGenderMatch = row.currentPlan.toLowerCase().indexOf(planFilter) !== -1 || !planFilter;
            const isPartialStatusMatch = row.status.toLowerCase().indexOf(statusFilter) !== -1 || !statusFilter;
            return isPartialNameMatch && isPartialGenderMatch && isPartialStatusMatch;
        });
    }



    constructor(
        private router: Router,
        private ecoleService: EcoleControllerService,
    ) {
        this._unsubscribeAll = new Subject();
        this.onDatatablessChanged = new BehaviorSubject({});
    }

    /**
     * rowDetailsToggleExpand
     *
     * @param row
     */
    rowDetailsToggleExpand(row) {
        this.tableRowDetails.rowDetail.toggleExpandRow(row);
    }

    ngOnInit(): void {

        this.findAllEcoles();
        this.onDatatablessChanged.pipe(takeUntil(this._unsubscribeAll)).subscribe(response => {
            if (!response) return;
            this.rows = response;
            this.tempData = this.rows;
            this.exportCSVData = this.rows;
        });
    }


    /**
     * filterUpdate
     *
     * @param event
     */
    filterUpdate(event) {
        // Reset ng-select on search


        const val = event.target.value.toLowerCase();

        // Filter Our Data
        const temp = this.tempData.filter(function (d) {
            return d.fullName.toLowerCase().indexOf(val) !== -1 || !val;
        });

        // Update The Rows
        this.rows = temp;
        // Whenever The Filter Changes, Always Go Back To The First Page
        this.table.offset = 0;
    }

    /**
     * Resolver
     *
     * @param {ActivatedRouteSnapshot} route
     * @param {RouterStateSnapshot} state
     * @returns {Observable<any> | Promise<any> | any}
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
        return new Promise<void>((resolve, reject) => {
            Promise.all([this.findAllEcoles()]).then(() => {
                resolve();
            }, reject);
        });
    }

    findAllEcoles(): void {
        this.ecoleService.findAllUsingGET2().subscribe(res => {
            this.rows = res;
            this.onDatatablessChanged.next(this.rows);
        });
    }

    newSchool(){
        this.router.navigate(['ecoles/newecole']);


    }

    joinAddressBlocks(address): string {
        if (!address || address.length < 1) return "N/A"
        return Object.values(address).join(" ");
    }

    deleteecole(id: any): Promise<any[]> {

        return new Promise((resolve, reject) => {

            this.ecoleService.deleteUsingDELETE2(id).subscribe((response) => {
                console.log(id);
                window.location.reload();


            }, reject);
        });
        window.location.reload();

    }

}
