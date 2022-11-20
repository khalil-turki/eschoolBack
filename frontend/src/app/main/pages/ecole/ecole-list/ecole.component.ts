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
    public ColumnMode = ColumnMode;
    @Input() ecoleDto: EcoleDto = {};
    public rows: Array<EcoleDto> = [];
    public onDatatablessChanged: BehaviorSubject<any>;

    private _unsubscribeAll: Subject<any>;
    private tempData = [];
    public exportCSVData;
    public kitchenSinkRows: any;
    public pageBasicText = 1;



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
     * Search (filter)
     *
     * @param event
     */
    filterUpdate(event) {
        const val = event.target.value.toLowerCase();

        // filter our data
        const temp = this.tempData.filter(function (d) {
            return d.nom.toLowerCase().indexOf(val) !== -1 || !val;
        });

        // update the rows
        this.rows = temp;
        // Whenever the filter changes, always go back to the first page

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

    joinAddressBlocks(address): string {
        if (!address || address.length < 1) return "N/A"
        return Object.values(address).join(" ");
    }
}
