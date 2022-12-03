import { Component, OnInit } from '@angular/core';
import {EcoleControllerService} from "../../../../../gs-api/src/services/ecole-controller.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {FormBuilder} from "@angular/forms";
import {CoreSidebarService} from "../../../../../@core/components/core-sidebar/core-sidebar.service";
import Swal from "sweetalert2";
import {EcoleDto} from "../../../../../gs-api/src/models/ecole-dto";


@Component({
  selector: 'app-ecole-new',
  templateUrl: './ecole-new.component.html',
  styleUrls: ['./ecole-new.component.scss']
})
export class EcoleNewComponent implements OnInit {
   ecoleDto: EcoleDto = {};

  public error = '';
  public submitted = false;
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
              private ecoleControllerService: EcoleControllerService
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

  }



}