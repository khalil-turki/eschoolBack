import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';


import SavePhotoParams = PhotoControllerService.SavePhotoUsingPOSTParams;
import {EtudiantDto} from "../../../../../../gs-api/src/models/etudiant-dto";
import {ParentDto} from "../../../../../../gs-api/src/models/parent-dto";
import {AdresseDto} from "../../../../../../gs-api/src/models/adresse-dto";
import {ClasseDto} from "../../../../../../gs-api/src/models/classe-dto";
import {EtudiantControllerService} from "../../../../../../gs-api/src/services/etudiant-controller.service";
import {ClasseControllerService} from "../../../../../../gs-api/src/services/classe-controller.service";
import {ParentControllerService} from "../../../../../../gs-api/src/services/parent-controller.service";
import {PhotoControllerService} from "../../../../../../gs-api/src/services/photo-controller.service";
import Swal from "sweetalert2";
import {AuthenticationService} from "../../../../../auth/service";

@Component({
  selector: 'app-nouveau-etudiant',
  templateUrl: './nouveau-etudiant.component.html',
  styleUrls: ['./nouveau-etudiant.component.css']
})
export class NouveauEtudiantComponent implements OnInit {

  etudiantDto:EtudiantDto={};
  parentDto:ParentDto={};
  adresseDto:AdresseDto={};
  errorMsg:Array<string> =[];
  listeClasse: Array<ClasseDto> = [];
  listeParent: any;
  classeDto:ClasseDto ={};
  file: File | null = null;
  imgUrl: string | ArrayBuffer = 'assets/images/etudiant.png';
  public currentUser: any;





  constructor(
    private router:  Router,
    private activatedRoute: ActivatedRoute,
    private etudiantService:EtudiantControllerService,
    private classeService:ClasseControllerService,
    private parentService:ParentControllerService,
    private photoService:PhotoControllerService,
    private authService: AuthenticationService

  ) { }

    ngOnInit(): void {
      this.parentService.findAllUsingGET5().subscribe(parents => {this.listeParent = parents;});
      this.classeService.findAllUsingGET1().subscribe(classes => {this.listeClasse = classes;});


      //recuperation des donnés pour la modification a partir de l'id passé a l url
  
      const idEtudiant = this.activatedRoute.snapshot.params['idEtudiant'];
      if (idEtudiant)
      {
        this.etudiantService.findByIdUsingGET4(idEtudiant)
        .subscribe(etudiant => {
          this.etudiantDto = etudiant;
          //on test si l objet est vide 
          this.classeDto = this.etudiantDto.classe ? this.etudiantDto.classe : {};
          this.adresseDto = this.etudiantDto.adresse ? this.etudiantDto.adresse : {};
          this.parentDto = this.etudiantDto.parent? this.etudiantDto.parent : {};
        });
      }
      this.currentUser = this.authService.currentUserValue;

    }
    
  cancelClick(): void {
    
      this.router.navigate(['etudiants']);
    
  }

  enregistrerEtudiant(): void {
    this.etudiantDto.classe = this.classeDto;
    this.etudiantDto.adresse=this.adresseDto;
    this.etudiantDto.parent=this.parentDto;
    this.etudiantService.createUsingPOST4(this.etudiantDto)

    .subscribe(etud => {
          this.savePhoto(etud.id, etud.nom);


    },

          error => {
            this.errorMsg = error.error.errors;
            console.log("helllloooooo" + this.errorMsg);
          }

    )


    Swal.fire({
      title: 'Patientez svp',
      html: 'En cours  ...',// add html attribute if you want or remove
      allowOutsideClick: false,
      onBeforeOpen: () => {
        Swal.showLoading();
      },
    });


    Swal.fire({
      icon: 'success',
      title: 'etudiant enregistrer!',
      text: 'A new user has been created.!',
    });
  }


  onFileInput(files: FileList | null): void {
    if (files) {
      this.file = files.item(0);
      if (this.file) {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(this.file);
        fileReader.onload = (event) => {
          if (fileReader.result) {
            this.imgUrl = fileReader.result;
          }
        };
      }
    }
  }

  savePhoto(idEtudiant?: number, titre?: string): void {
    if (idEtudiant && titre && this.file) {
      const params: SavePhotoParams = {
        id: idEtudiant,
        file: this.file,
        title: titre,
        context: 'etudiant'
      };
      this.photoService.savePhotoUsingPOST(params)
      .subscribe(res => {
        this.router.navigate(['etudiants']);
      });
    } else {
      this.router.navigate(['etudiants']);
    }
  }



}
