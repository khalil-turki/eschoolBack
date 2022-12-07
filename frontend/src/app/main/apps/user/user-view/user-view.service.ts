import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';

import { BehaviorSubject, Observable } from 'rxjs';
import {environment} from "../../../../../environments/environment";

@Injectable()
export class UserViewService implements Resolve<any> {
  public rows: any;
  public onUserViewChanged: BehaviorSubject<any>;
  public id;


  constructor(private _httpClient: HttpClient) {
    this.onUserViewChanged = new BehaviorSubject({});
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    let currentId = Number(route.paramMap.get('id'));
    return new Promise<void>((resolve, reject) => {
      Promise.all([this.getApiData(currentId)]).then(() => {
        resolve();
      }, reject);
    });
  }

  /**
   * Get rows
   */
  getApiData(id: number): Promise<any[]> {
    const url = `${environment.apiUrl}/user/${id}`;

    return new Promise((resolve, reject) => {
      this._httpClient.get(url).subscribe((response: any) => {
        this.rows = response;
        this.onUserViewChanged.next(this.rows);
        resolve(this.rows);
      }, reject);
    });
  }
}
