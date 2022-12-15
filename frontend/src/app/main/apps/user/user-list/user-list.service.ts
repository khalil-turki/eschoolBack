import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';

import { BehaviorSubject, Observable } from 'rxjs';
import {environment} from "../../../../../environments/environment";

@Injectable()
export class UserListService implements Resolve<any> {
  public rows: any;
  public onUserListChanged: BehaviorSubject<any>;

  constructor(private _httpClient: HttpClient) {
    // Set the defaults
    this.onUserListChanged = new BehaviorSubject({});
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    return new Promise<void>((resolve, reject) => {
      Promise.all([this.getDataTableRows()]).then(() => {
        resolve();
      }, reject);
    });
  }

  async getDataTableRows(): Promise<void> {
      await this._httpClient.get(`${environment.apiUrl}/user/`).subscribe((response: any) => {
        this.rows = response;
        this.onUserListChanged.next(this.rows);
      });
  }

  deleteUser(id: number) {
    return this._httpClient.delete(`${environment.apiUrl}/user/${id}`);
  }
}
