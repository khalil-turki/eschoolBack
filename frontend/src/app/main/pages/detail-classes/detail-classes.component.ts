import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import {ClasseDto} from "../../../../gs-api/src/models/classe-dto";
import {ClasseControllerService} from "../../../../gs-api/src/services/classe-controller.service";


@Component({
  selector: 'app-detail-classes',
  templateUrl: './detail-classes.component.html',
  styleUrls: ['./detail-classes.component.css']
})
export class DetailClassesComponent implements OnInit {

  
  @Input()
  classeDto: ClasseDto = {};
  @Output()
  suppressionResult = new EventEmitter();
  errorMsgs:Array<string> =[];


  constructor(
    private router : Router,
    private classeService:ClasseControllerService
  ) { }

  ngOnInit(): void {
  }


  modifierClasse(idClasse?:number):void
  {
    this.router.navigate(['nvClasse', idClasse]);
  }

 
  
  confirmerEtSupprimer(): void {
    if (this.classeDto.id) {
    this.classeService.deleteUsingDELETE1(this.classeDto.id)
    
    .subscribe(res => {
      this.suppressionResult.emit('success');
      
    }, error => {
    
      this.suppressionResult.emit(error.error.error);
      
    });
    location.reload();
  }





  
}



}
