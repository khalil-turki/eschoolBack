/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { AuthReqDto } from '../models/auth-req-dto';
import { EtudiantDto } from '../models/etudiant-dto';

/**
 * Authentication Controller
 */
@Injectable({
  providedIn: 'root',
})
class AuthenticationControllerService extends __BaseService {
  static readonly refreshtokenUsingGETPath = '/refreshtoken';
  static readonly createAuthenticationTokenUsingPOSTPath = '/signin';
  static readonly saveUserUsingPOSTPath = '/signup';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * refreshtoken
   * @return OK
   */
  refreshtokenUsingGETResponse(): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/refreshtoken`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<{}>;
      })
    );
  }
  /**
   * refreshtoken
   * @return OK
   */
  refreshtokenUsingGET(): __Observable<{}> {
    return this.refreshtokenUsingGETResponse().pipe(
      __map(_r => _r.body as {})
    );
  }

  /**
   * createAuthenticationToken
   * @param authenticationRequest authenticationRequest
   * @return OK
   */
  createAuthenticationTokenUsingPOSTResponse(authenticationRequest: AuthReqDto): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = authenticationRequest;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/signin`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<{}>;
      })
    );
  }
  /**
   * createAuthenticationToken
   * @param authenticationRequest authenticationRequest
   * @return OK
   */
  createAuthenticationTokenUsingPOST(authenticationRequest: AuthReqDto): __Observable<{}> {
    return this.createAuthenticationTokenUsingPOSTResponse(authenticationRequest).pipe(
      __map(_r => _r.body as {})
    );
  }

  /**
   * saveUser
   * @param user user
   * @return OK
   */
  saveUserUsingPOSTResponse(user: EtudiantDto): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = user;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/signup`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<{}>;
      })
    );
  }
  /**
   * saveUser
   * @param user user
   * @return OK
   */
  saveUserUsingPOST(user: EtudiantDto): __Observable<{}> {
    return this.saveUserUsingPOSTResponse(user).pipe(
      __map(_r => _r.body as {})
    );
  }
}

module AuthenticationControllerService {
}

export { AuthenticationControllerService }
