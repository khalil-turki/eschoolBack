/* tslint:disable */
import { Injectable } from '@angular/core';

/**
 * Global configuration for Api services
 */
@Injectable({
  providedIn: 'root',
})
export class ApiConfiguration {
  rootUrl: string = '//http://172.18.137.75:8087';
}

export interface ApiConfigurationInterface {
  rootUrl?: string;
}
