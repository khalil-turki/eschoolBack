import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { BehaviorSubject } from 'rxjs';
import {environment} from "../../../../../environments/environment";
import {Notification} from "./notification.model";

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {
  // Public
  public apiData = [];
  public onApiDataChange: BehaviorSubject<any>;

  /**
   *
   * @param {HttpClient} _httpClient
   */
  constructor(private _httpClient: HttpClient) {
    this.onApiDataChange = new BehaviorSubject('');
    this.getNotificationsData();
  }

  /**
   * Get Notifications Data
   */
  getNotificationsData(): Promise<any[]> {
    return new Promise((resolve, reject) => {
      this._httpClient.get(`${environment.apiUrl}/notifications/me`).subscribe((response: any) => {
        this.apiData = response;
        this.onApiDataChange.next(this.apiData);
        resolve(this.apiData);
      }, reject);
    });
  }

  async markAllAsRead() {
    await this._httpClient.post(`${environment.apiUrl}/notifications/markAllAsRead`, {}).toPromise();
  }

  async markAsRead(notification: Notification) {
    await this._httpClient.post(`${environment.apiUrl}/notifications//markAsRead/${notification.id}`, {}).toPromise();
  }
}
