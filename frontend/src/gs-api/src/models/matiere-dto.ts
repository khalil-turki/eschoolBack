/* tslint:disable */
import { ProfesseurDto } from './professeur-dto';
export interface MatiereDto {
  description?: string;
  id?: number;
  nom?: string;
  professeurs?: Array<ProfesseurDto>;
}
