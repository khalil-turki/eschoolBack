import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {environment} from 'environments/environment';
import {User, Role} from 'app/auth/models';
import {ToastrService} from 'ngx-toastr';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  public currentUser: Observable<User>;

  private currentUserSubject: BehaviorSubject<User>;

  constructor(private _http: HttpClient, private _toastrService: ToastrService) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  // getter: currentUserValue
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  get isAdmin() {
    return this.currentUser && this.currentUserSubject.value.role === Role.Admin;
  }

  get isProfesseur() {
    return this.currentUser && this.currentUserSubject.value.role === Role.Professeur;
  }

  get isParent() {
    return this.currentUser && this.currentUserSubject.value.role === Role.Parent;
  }

  get isEtudiant() {
    return this.currentUser && this.currentUserSubject.value.role === Role.Etudiant;
  }

  login(email: string, password: string, totp: string, rememberMe: boolean) {
    return this._http
      .post<any>(`${environment.apiUrl}/auth/login`, {username: email, password, rememberMe, totp})
      .pipe(
        map(user => {
          if (user && user.token) {
            localStorage.setItem('currentUser', JSON.stringify(user));
            setTimeout(() => {
              this._toastrService.success(
                'You have successfully logged in as an ' +
                user.role +
                ' Kaddem. Now you can start to explore. Enjoy! 🎉',
                '👋 Welcome, ' + user.prenom + '!',
                {toastClass: 'toast ngx-toastr', closeButton: true}
              );
            }, 2500);
            this.currentUserSubject.next(user);
          }
          return user;
        })
      );
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
