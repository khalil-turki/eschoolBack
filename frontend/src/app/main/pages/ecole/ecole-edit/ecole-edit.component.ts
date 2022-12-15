import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {FlatpickrOptions} from "ng2-flatpickr";
import {Observable, Subject} from "rxjs";
import {ActivatedRoute, ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {takeUntil} from "rxjs/operators";
import {cloneDeep} from 'lodash';
import {EcoleControllerService} from "gs-api/src/services/ecole-controller.service";
import {EcoleDto} from "gs-api/src/models/ecole-dto";
import {map} from 'rxjs/operators';
import Swal from "sweetalert2";
import {AdresseDto} from "../../../../../gs-api/src/models/adresse-dto";


@Component({
    selector: 'app-ecole-edit',
    templateUrl: './ecole-edit.component.html',
    styleUrls: ['./ecole-edit.component.scss']
})
export class EcoleEditComponent implements OnInit, OnDestroy {
    public url = this.router.url;
    public urlLastValue;
    public rows;
    public currentRow;
    public tempRow;
    public avatarImage: string;
    public adresseDto:AdresseDto={};


    ecoleDto: EcoleDto = {};


    @ViewChild('accountForm') accountForm: NgForm;

    public birthDateOptions: FlatpickrOptions = {
        altInput: true
    };

    public selectMultiLanguages = ['English', 'Spanish', 'French', 'Russian', 'German', 'Arabic', 'Sanskrit'];
    public selectMultiLanguagesSelected = [];

    // Private
    private _unsubscribeAll: Subject<any>;


    constructor(

        private router: Router,
        private ecoleService: EcoleControllerService,
        private activatedRoute: ActivatedRoute,
        private ecoleControllerService: EcoleControllerService,
        private _router: Router,

    ) {
        this._unsubscribeAll = new Subject();
        this.urlLastValue = this.url.substr(this.url.lastIndexOf('/') + 1);


    }

    // Public Methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Reset Form With Default Values
     */
    resetFormWithDefaultValues() {
        this.accountForm.resetForm(this.tempRow);
    }

    /**
     * Upload Image
     *
     * @param event
     */
    uploadImage(event: any) {
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();

            reader.onload = (event: any) => {
                this.avatarImage = event.target.result;
            };

            reader.readAsDataURL(event.target.files[0]);
        }
    }

    /**
     * Submit
     *
     * @param form
     */


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

    findAllEcoles(): Promise<any[]> {
        return new Promise((resolve, reject) => {

            this.ecoleService.findAllUsingGET2().subscribe(res => {
                this.rows = res;
                this.ecoleService.onUserEditChanged.next(this.rows);
                resolve(this.rows);
            }, reject);
        });
    }

    submit(form) {
        if (form.valid) {
            return new Promise((resolve, reject) => {
                Swal.fire({
                    title: 'Patientez svp',
                    html: 'En cours  ...',// add html attribute if you want or remove
                    allowOutsideClick: false,
                    onBeforeOpen: () => {
                        Swal.showLoading();
                    },
                });
                console.log(this.ecoleDto)
                this.ecoleControllerService.createUsingPOST2(this.ecoleDto).subscribe(response => {
                        this._router.navigate(['ecoles/listecoles']);
                        Swal.fire({
                            icon: 'success',
                            title: 'Updated!',
                            text: 'Your School has been updated.!',
                        });
                        resolve(response);
                    }
                    , rejectMessage => {
                        Swal.fire({
                            icon: 'error',
                            title: 'Failed!',
                            text: 'Your School has not been updated. Reason: ' + rejectMessage,
                            customClass: {
                                confirmButton: 'btn btn-danger'
                            }
                        });
                        reject();
                    }
                );
            });
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Failed!',
                text: 'Invalid data',
                customClass: {
                    confirmButton: 'btn btn-danger'
                }
            });
        }
    }


    // Lifecycle Hooks
    // -----------------------------------------------------------------------------------------------------
    /**
     * On init
     */
    ngOnInit(): void {
        const idClasse = this.activatedRoute.snapshot.params['id'];
        if (idClasse) {
            this.ecoleService.findByIdUsingGET2(idClasse)
                .subscribe(classe => {
                    this.ecoleDto = classe;
                    this.adresseDto = this.ecoleDto.adresse ? this.ecoleDto.adresse : {};

                });
        }
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
