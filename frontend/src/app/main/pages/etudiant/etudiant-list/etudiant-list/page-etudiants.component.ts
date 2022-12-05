import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
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
  public pageBasicText = 1;
  public exportCSVData;






  constructor(
    private router: Router,
    private etudiantService:EtudiantControllerService,
    private activatedRoute: ActivatedRoute

  ) { }


  ngOnInit(): void {
    const idClasse = this.activatedRoute.snapshot.params['idClasse'];
    if (idClasse)
    {
      this.findEtudiantsByIdClass(idClasse);
    }
    else {
      this.findAllEtudiants();
    }

  }

  findAllEtudiants(): void {
    this.etudiantService.findAllUsingGET4().subscribe(res => {
      this.listEtudiants = res;
      this.exportCSVData = this.listEtudiants;

    });
  }

  findEtudiantsByIdClass(idClasse){
    this.etudiantService.findEtudiantsByClasseIdUsingGET(idClasse).subscribe(res => {
      this.listEtudiants = res;
      this.exportCSVData = this.listEtudiants;

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
