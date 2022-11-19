import {Component, Injectable, Input, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {Router} from "@angular/router";
import {EcoleControllerService} from "gs-api/src/services/ecole-controller.service";
import {DatatableComponent, ColumnMode} from "@swimlane/ngx-datatable";
import {BehaviorSubject, Subject} from "rxjs";
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
export class EcoleComponent implements OnInit {
    @ViewChild('tableRowDetails') tableRowDetails: any;
    public ColumnMode = ColumnMode;
    @Input() ecoleDto: EcoleDto = {};
    public rows: Array<EcoleDto> = [];
    public onDatatablessChanged: BehaviorSubject<any>;

    private _unsubscribeAll: Subject<any>;
    private tempData = [];


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
            if(!response) return;
            this.rows = response;
            this.tempData = this.rows;
        });
    }

    findAllEcoles(): void {
        this.ecoleService.findAllUsingGET2().subscribe(res => {
            this.rows = res;
            this.onDatatablessChanged.next(this.rows);
        });
    }

    joinAddressBlocks(address): string{
        if(!address || address.length < 1) return "N/A"
        return Object.values(address).join(" ");
    }
}
