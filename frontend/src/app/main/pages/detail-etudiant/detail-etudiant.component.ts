import { outputAst } from '@angular/compiler';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import {EtudiantDto} from "../../../../gs-api/src/models/etudiant-dto";
import {EtudiantControllerService} from "../../../../gs-api/src/services/etudiant-controller.service";
import {ClasseDto} from "../../../../gs-api/src/models/classe-dto";
import Swal from "sweetalert2";

@Component({
  selector: 'app-detail-etudiant',
  templateUrl: './detail-etudiant.component.html',
  styleUrls: ['./detail-etudiant.component.css']
})
export class DetailEtudiantComponent implements OnInit {

  @Input()
  etudiantDto: EtudiantDto = {};

  @Input()
  classeDto: ClasseDto = {};

  @Output()
  suppressionResult = new EventEmitter();


  errorMsgs: Array<string> = [];

  constructor(
      private router: Router,
      private etudiantService: EtudiantControllerService
  ) {
  }

  ngOnInit(): void {

  }


  modifierEtudiant(idEtudiant?: number): void {
    this.router.navigate(['nvEtudiant', idEtudiant]);

  }







  confirmerEtSupprimer(idEtudiant): void {
    Swal.fire({
      title: 'Do you want to delete this student ?',
      showCancelButton: true,
      confirmButtonText: 'Save',
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        if (this.etudiantDto.id ) {

          this.etudiantService.deleteUsingDELETE4(this.etudiantDto.id)

              .subscribe(res => {
                this.suppressionResult.emit('success');

              }, error => {

                this.suppressionResult.emit(error.error.error);

              });

          window.location.reload();
        }

        Swal.fire('Deleted !', '', 'success')
      } else if (!result.isConfirmed) {
        Swal.fire('Changes are not saved', '', 'info')
      }
    })











  
}



}

