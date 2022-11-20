/* tslint:disable */
import {AdresseDto} from "./adresse-dto";
import {ClasseDto} from "./classe-dto";
import {ParentDto} from "./parent-dto";

export interface EtudiantDto {
  classe?: ClasseDto;
  dateDeNaissance?: string;
  email?: string;
  id?: number;
  nom?: string;
  numTel?: string;
  parent?: ParentDto;
  prenom?: string;
  adresse?: AdresseDto;
  photo?: string;

}
