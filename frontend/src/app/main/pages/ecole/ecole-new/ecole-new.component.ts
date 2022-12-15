import { Component, OnInit } from '@angular/core';
import {EcoleControllerService} from "../../../../../gs-api/src/services/ecole-controller.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {FormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {CoreSidebarService} from "../../../../../@core/components/core-sidebar/core-sidebar.service";
import Swal from "sweetalert2";
import {EcoleDto} from "../../../../../gs-api/src/models/ecole-dto";
import {Subject} from "rxjs";
import {takeUntil} from "rxjs/operators";
import {AuthenticationService} from "../../../../auth/service";
import {AdresseDto} from "../../../../../gs-api/src/models/adresse-dto";


@Component({
  selector: 'app-ecole-new',
  templateUrl: './ecole-new.component.html',
  styleUrls: ['./ecole-new.component.scss']
})
export class EcoleNewComponent implements OnInit {
   ecoleDto: EcoleDto = {};
  adresseDto:AdresseDto={};
  public error = '';
  public submitted = false;
  private _unsubscribeAll: Subject<any>;
  public currentUser: any;


  public selectStatus: any = [
    { name: 'All', value: '' },
    { name: 'Pending', value: 'Pending' },
    { name: 'Active', value: 'Active' },
    { name: 'Inactive', value: 'Inactive' }
  ];

  /**
   * Submit
   *
   * @param form
   * @param ecoleDto
   * @param ecoleControllerService : EcoleControllerService
   *
   */


  submit(form) {

    if (form.valid) {
      this.ecoleDto.adresse=this.adresseDto;

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
                title: 'Created!',
                text: 'A new school has been created.!',
              });
              resolve(response);
            }
            , rejectMessage => {
              Swal.fire({
                icon: 'error',
                title: 'Failed!',
                text: 'Your new school has not been created. Reason: ' + rejectMessage,
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

  /**
   * Constructor
   *
   * @param {CoreSidebarService} _coreSidebarService
   * @param _httpClient
   * @param toastrService
   * @param _formBuilder
   * @param _route
   * @param _router
   * @param ecoleControllerService
   */

  constructor(private _coreSidebarService: CoreSidebarService,
              private _httpClient: HttpClient,
              private toastrService: ToastrService,
              private _formBuilder: FormBuilder,
              private _route: ActivatedRoute,
              private _router: Router,
              private ecoleControllerService: EcoleControllerService,
              private userService:AuthenticationService

  ) {
  }

  /**
   * Toggle the sidebar
   *
   * @param name
   */
  toggleSidebar(name): void {
    this._coreSidebarService.getSidebarRegistry(name).toggleOpen();
  }
  toggleSidebarClose(name): void {
    this._coreSidebarService.getSidebarRegistry(name).close();
  }



  ngOnInit(): void {
    this.registerForm = this._formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
    this.currentUser = this.userService.currentUserValue;
    this.adresseDto = this.ecoleDto.adresse ? this.ecoleDto.adresse : {};

  }


  // Public
  public coreConfig: any;
  public passwordTextType: boolean;
  public registerForm: UntypedFormGroup;



  // convenience getter for easy access to form fields
  get f() {
    return this.registerForm.controls;
  }

  /**
   * Toggle password
   */
  togglePasswordTextType() {
    this.passwordTextType = !this.passwordTextType;
  }

  /**
   * On Submit
   */
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
  }

  // Lifecycle Hooks
  // -----------------------------------------------------------------------------------------------------
  Unothorized: any;



  /**
   * On destroy
   */
  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions
    this._unsubscribeAll.next();
    this._unsubscribeAll.complete();
  }



}