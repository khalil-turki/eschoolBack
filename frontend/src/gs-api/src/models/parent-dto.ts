/* tslint:disable */
import { EtudiantDto } from './etudiant-dto';
export interface ParentDto {
  cin?: string;
  dateDeNaissance?: string;
  email?: string;
  etudiants?: Array<EtudiantDto>;
  id?: number;
  nom?: string;
  numTel?: string;
  prenom?: string;
}
