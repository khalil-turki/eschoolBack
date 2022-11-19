/* tslint:disable */
import { ClasseDto } from './classe-dto';
import { MatiereDto } from './matiere-dto';
export interface ProfesseurDto {
  classes?: Array<ClasseDto>;
  dateDeNaissance?: string;
  email?: string;
  id?: number;
  matieres?: Array<MatiereDto>;
  nom?: string;
  numTel?: string;
  prenom?: string;
}
