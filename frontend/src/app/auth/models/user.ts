import { Role } from './role';

export class User {
  id: number;
  email: string;
  password: string;
  prenom: string;
  nom: string;
  avatar: string;
  role: Role;
  token?: string;
  numTel: string;
  plainPassword: string;
  enabled: boolean;
  dateDeNaissance: Date;
  gender: string;
  isUsing2FA: boolean;
}
