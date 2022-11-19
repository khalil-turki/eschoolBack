/* tslint:disable */
import { ProfesseurDto } from './professeur-dto';
export interface ClasseDto {
  creationDate?: string;
  id?: number;
  lastModifiedDate?: string;
  nomClasse?: string;
  professeurs?: Array<ProfesseurDto>;
  specialite?: string;
}
