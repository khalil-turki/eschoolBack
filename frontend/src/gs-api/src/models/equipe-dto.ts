/* tslint:disable */
import { DetailEquipeDto } from './detail-equipe-dto';
export interface EquipeDto {
  detailEquipe?: DetailEquipeDto;
  id?: number;
  niveau?: 'EXPERT' | 'JUNIOR' | 'SENIOR';
  nomEquipe?: string;
}
