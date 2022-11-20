import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {EtudiantDto} from "../../../../../../gs-api/src/models/etudiant-dto";
import {EtudiantControllerService} from "../../../../../../gs-api/src/services/etudiant-controller.service";

@Component({
  selector: 'app-page-etudiants',
  templateUrl: './page-etudiants.component.html',
  styleUrls: ['./page-etudiants.component.css']
})
export class PageEtudiantsComponent implements OnInit {

  listEtudiants : Array<EtudiantDto> = [];
  errorMsgs:Array<string> =[];
  searchText:any;


  constructor(
    private router: Router,
    private etudiantService:EtudiantControllerService) { }

  ngOnInit(): void {
    this.findAllEtudiants();
  }

  findAllEtudiants(): void {
    this.etudiantService.findAllUsingGET4().subscribe(res => {
      this.listEtudiants = res;
      console.log(this.listEtudiants);
    });
  }

  nvEtudiant(): void {
    this.router.navigate(['nvEtudiant']);
  }

  handleSuppression(event:any):void{
    if (event === 'success') {
      this.findAllEtudiants

    }
    else { this.errorMsgs =event ;}

  }


}
