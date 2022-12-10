import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';

import {BehaviorSubject, Observable} from 'rxjs';
import {environment} from "../../../../../environments/environment";

@Injectable()
export class UserEditService implements Resolve<any> {
    public apiData: any;
    public onUserEditChanged: BehaviorSubject<any>;

    constructor(private _httpClient: HttpClient) {
        this.onUserEditChanged = new BehaviorSubject({});
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
        return new Promise<void>((resolve, reject) => {
            const id = route.paramMap.get('id');
            this.getApiData(id).then(() => {
                resolve();
            }, reject);
        });
    }

    getApiData(id): Promise<any[]> {
        return new Promise((resolve, reject) => {
            this._httpClient.get(`${environment.apiUrl}/user/${id}`).subscribe((response: any) => {
                this.apiData = response;
                this.onUserEditChanged.next(this.apiData);
                resolve(this.apiData);
            }, reject);
        });
    }

    saveUser(user): Promise<any> {
        return new Promise((resolve, reject) => {
            this._httpClient.patch(`${environment.apiUrl}/user/${user.id}`, user, {
                headers: {
                    'Content-Type': 'application/merge-patch+json'
                }
            }).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }
}
