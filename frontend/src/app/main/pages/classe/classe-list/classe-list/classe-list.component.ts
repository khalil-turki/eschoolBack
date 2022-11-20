import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {EcoleDto} from "../../../../../../gs-api/src/models/ecole-dto";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {ClasseDto} from "../../../../../../gs-api/src/models/classe-dto";
import {EcoleControllerService} from "../../../../../../gs-api/src/services/ecole-controller.service";
import {ClasseControllerService} from "../../../../../../gs-api/src/services/classe-controller.service";
import {takeUntil} from "rxjs/operators";
import {DatatableComponent, ColumnMode} from "@swimlane/ngx-datatable";


@Component({
  selector: 'app-classe-list',
  templateUrl: './classe-list.component.html',
  styleUrls: ['./classe-list.component.scss']
})
export class ClasseListComponent implements OnInit, Resolve<any>{
  @ViewChild('tableRowDetails') tableRowDetails: any;
  public ColumnMode = ColumnMode;
  @Input() classeDto: ClasseDto = {};
  public rows: Array<ClasseDto> = [];
  public onDatatablessChanged: BehaviorSubject<any>;

  private _unsubscribeAll: Subject<any>;
  private tempData = [];
  public exportCSVData;
  public kitchenSinkRows: any;
  public pageBasicText = 1;


  constructor(private router: Router,
              private classeService: ClasseControllerService,
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

    this.findAllClasses();
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
      Promise.all([this.findAllClasses()]).then(() => {
        resolve();
      }, reject);
    });
  }

  findAllClasses(): void {
    this.classeService.findAllUsingGET1().subscribe(res => {
      this.rows = res;
      this.onDatatablessChanged.next(this.rows);
    });
  }



  joinAddressBlocks(address): string {
    if (!address || address.length < 1) return "N/A"
    return Object.values(address).join(" ");
  }

  modifierClasse(idClasse: number): void {
    this.router.navigate(['nvClasse', idClasse]);
    console.log(idClasse);
  }


    supprimerClasse(classeDto)
    {
        this.classeService.deleteUsingDELETE1(classeDto.id)
        .subscribe(res => {
        this.findAllClasses();
        });
    }

    nouveauClasse()
    {
        this.router.navigate(['nouveau-classe']);
    }
  confirmerEtSupprimer()
    {

    }

}
