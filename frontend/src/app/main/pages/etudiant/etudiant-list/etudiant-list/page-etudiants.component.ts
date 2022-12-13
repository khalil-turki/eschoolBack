import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EtudiantDto} from "../../../../../../gs-api/src/models/etudiant-dto";
import {EtudiantControllerService} from "../../../../../../gs-api/src/services/etudiant-controller.service";
import {AuthenticationService, UserService} from "../../../../../auth/service";

@Component({
  selector: 'app-page-etudiants',
  templateUrl: './page-etudiants.component.html',
  styleUrls: ['./page-etudiants.component.css']
})
export class PageEtudiantsComponent implements OnInit {

  listEtudiants : Array<EtudiantDto> = [];
  listEtudiants2 : Array<EtudiantDto> = [];
  errorMsgs:Array<string> =[];
  searchText:any;
  public page=0;
  public pageBasicText = 1;
  public exportCSVData;
  public currentUser: any;
  public index =20;







  constructor(
    private router: Router,
    private etudiantService:EtudiantControllerService,
    private activatedRoute: ActivatedRoute,
    private userService:AuthenticationService

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

    this.currentUser = this.userService.currentUserValue;


  }



  findAllEtudiants(): void {
    this.etudiantService.listUsingGET4(this.page).subscribe(res => {
      this.listEtudiants = res;
      this.exportCSVData = this.listEtudiants;

    });

    this.etudiantService.listUsingGET4(this.page+1).subscribe(res => {
      this.listEtudiants2 = res;

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



  next(): void {
    if(this.listEtudiants.length>2 && this.listEtudiants2.length>0) {
        this.page++;

      if(this.listEtudiants2.length>0)
      {this.index++;}

    }

    this.findAllEtudiants();

  }

  previous(): void {
    if(this.page>0) {
      this.page--;

      this.findAllEtudiants();
    }
  }



  }
