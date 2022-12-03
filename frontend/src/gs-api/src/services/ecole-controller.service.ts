/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import {BehaviorSubject, Observable as __Observable} from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableEcoleDto } from '../models/iterable-ecole-dto';
import { EcoleDto } from '../models/ecole-dto';
import { JsonMergePatch } from '../models/json-merge-patch';
import {ClasseDto} from "../models/classe-dto";

/**
 * Ecole Controller
 */
@Injectable({
  providedIn: 'root',
})
class EcoleControllerService extends __BaseService {
  static readonly findAllUsingGET2Path = '/ecoles';
  static readonly createUsingPOST2Path = '/ecoles';
  static readonly findByIdUsingGET2Path = '/ecoles/{id}';
  static readonly updateUsingPUT2Path = '/ecoles/{id}';
  static readonly deleteUsingDELETE2Path = '/ecoles/{id}';
  static readonly patchUsingPATCH2Path = '/ecoles/{id}';

  public onUserEditChanged: BehaviorSubject<any>;


  constructor(
    config: __Configuration,
    http: HttpClient

  ) {
    super(config, http);
    this.onUserEditChanged = new BehaviorSubject({});

  }

  /**
   * findAll
   * @return OK
   */
  findAllUsingGET2Response():__Observable<__StrictHttpResponse<Array<EcoleDto>>>  {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/ecoles`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<EcoleDto>>;
      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET2(): __Observable<Array<EcoleDto>> {
    return this.findAllUsingGET2Response().pipe(
        __map(_r => _r.body as Array<EcoleDto>)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST2Response(dto: EcoleDto): __Observable<__StrictHttpResponse<EcoleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/ecoles`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EcoleDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST2(dto: EcoleDto): __Observable<EcoleDto> {
    return this.createUsingPOST2Response(dto).pipe(
      __map(_r => _r.body as EcoleDto)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET2Response(id: number): __Observable<__StrictHttpResponse<EcoleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/ecoles/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EcoleDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET2(id: number): __Observable<EcoleDto> {
    return this.findByIdUsingGET2Response(id).pipe(
      __map(_r => _r.body as EcoleDto)
    );
  }

  /**
   * update
   * @param params The `EcoleControllerService.UpdateUsingPUT2Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT2Response(params: EcoleControllerService.UpdateUsingPUT2Params): __Observable<__StrictHttpResponse<EcoleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/ecoles/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EcoleDto>;
      })
    );
  }
  /**
   * update
   * @param params The `EcoleControllerService.UpdateUsingPUT2Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT2(params: EcoleControllerService.UpdateUsingPUT2Params): __Observable<EcoleDto> {
    return this.updateUsingPUT2Response(params).pipe(
      __map(_r => _r.body as EcoleDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE2Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/ecoles/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE2(id: number): __Observable<null> {
    return this.deleteUsingDELETE2Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `EcoleControllerService.PatchUsingPATCH2Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH2Response(params: EcoleControllerService.PatchUsingPATCH2Params): __Observable<__StrictHttpResponse<EcoleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/ecoles/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EcoleDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `EcoleControllerService.PatchUsingPATCH2Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH2(params: EcoleControllerService.PatchUsingPATCH2Params): __Observable<EcoleDto> {
    return this.patchUsingPATCH2Response(params).pipe(
      __map(_r => _r.body as EcoleDto)
    );
  }
}

module EcoleControllerService {

  /**
   * Parameters for updateUsingPUT2
   */
  export interface UpdateUsingPUT2Params {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: EcoleDto;
  }

  /**
   * Parameters for patchUsingPATCH2
   */
  export interface PatchUsingPATCH2Params {

    /**
     * patch
     */
    patch: JsonMergePatch;

    /**
     * id
     */
    id: number;
  }
}

export { EcoleControllerService }
