import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {ClasseDto} from "../../../../../gs-api/src/models/classe-dto";
import {ClasseControllerService} from "../../../../../gs-api/src/services/classe-controller.service";


@Component({
  selector: 'app-nouveau-classe',
  templateUrl: './nouveau-classe.component.html',
  styleUrls: ['./nouveau-classe.component.css']
})
export class NouveauClasseComponent implements OnInit {

  classeDto:ClasseDto={};
  errorMsg:Array<string> =[];

  constructor(
    private router:  Router,
    private activatedRoute: ActivatedRoute,
    private classeService:ClasseControllerService
    
    ) { }

    ngOnInit(): void {
      const idClasse = this.activatedRoute.snapshot.params['idClasse'];
      if (idClasse) {
        this.classeService.findByIdUsingGET1(idClasse)
        .subscribe(classe => {
          this.classeDto = classe;
        });
      }
    }
  
  cancelClick(): void {
    
    this.router.navigate(['classes']);
}

enregistrerClasse(): void {
  this.classeService.createUsingPOST1(this.classeDto)
  .subscribe(res => {
    this.router.navigate(['classes']);
  }, error => {
    this.errorMsg = error.error.errors;
  });
}
}


  