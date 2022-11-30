import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {EcoleDto} from "../../../../../../gs-api/src/models/ecole-dto";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {ClasseDto} from "../../../../../../gs-api/src/models/classe-dto";
import {EcoleControllerService} from "../../../../../../gs-api/src/services/ecole-controller.service";
import {ClasseControllerService} from "../../../../../../gs-api/src/services/classe-controller.service";
import {takeUntil} from "rxjs/operators";
import {DatatableComponent, ColumnMode} from "@swimlane/ngx-datatable";
import {EtudiantControllerService} from "../../../../../../gs-api/src/services/etudiant-controller.service";
import {EtudiantDto} from "../../../../../../gs-api/src/models/etudiant-dto";
import Swal from "sweetalert2";
import {msg} from "ng-packagr/lib/utils/log";


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
  listEtudiantsByClass : Array<EtudiantDto> =[];
  public onDatatablessChanged: BehaviorSubject<any>;

  private _unsubscribeAll: Subject<any>;
  private tempData = [];
  public exportCSVData;
  public kitchenSinkRows: any;
  public pageBasicText = 1;


  constructor(private router: Router,
              private classeService: ClasseControllerService,
              private  etudiantService:EtudiantControllerService
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
    if (!address || address.length < 1) return "28"
    return Object.values(this.rows).join(" ");
  }

  modifierClasse(idClasse: any): void {
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
        this.router.navigate(['nvClasse']);
    }


  delete(id) : void
  {
    Swal.fire({
      title: 'Do you want to delete this class ?',
      showCancelButton: true,
      confirmButtonText: 'Save',
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {


          this.classeService.deleteUsingDELETE1(id).subscribe(response => {
            window.location.reload();
            Swal.fire('Class deleted succefully!', '', 'success')


          });

        if (this.classeService.findByIdUsingGET1(id)) {
          Swal.fire('cannot delete this class !', '', 'error')

        }

      }

     })

  }

  findEtudiantsByIdClass(idClasse:number): void {
    this.etudiantService.findEtudiantsByClasseIdUsingGET(idClasse).subscribe(res => {
      this.listEtudiantsByClass = res;
    });
    this.router.navigate(['etudiants']);
  }

  findAllEtudiants(): void {
    this.etudiantService.findAllUsingGET4().subscribe(res => {
      this.listEtudiantsByClass = res;
    });
  }


}
