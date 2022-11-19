/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableEquipeDto } from '../models/iterable-equipe-dto';
import { EquipeDto } from '../models/equipe-dto';
import { JsonMergePatch } from '../models/json-merge-patch';

/**
 * Equipe Controller
 */
@Injectable({
  providedIn: 'root',
})
class EquipeControllerService extends __BaseService {
  static readonly findAllUsingGET3Path = '/equipes';
  static readonly createUsingPOST3Path = '/equipes';
  static readonly findByIdUsingGET3Path = '/equipes/{id}';
  static readonly updateUsingPUT3Path = '/equipes/{id}';
  static readonly deleteUsingDELETE3Path = '/equipes/{id}';
  static readonly patchUsingPATCH3Path = '/equipes/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * findAll
   * @return OK
   */
  findAllUsingGET3Response(): __Observable<__StrictHttpResponse<IterableEquipeDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/equipes`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<IterableEquipeDto>;
      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET3(): __Observable<IterableEquipeDto> {
    return this.findAllUsingGET3Response().pipe(
      __map(_r => _r.body as IterableEquipeDto)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST3Response(dto: EquipeDto): __Observable<__StrictHttpResponse<EquipeDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/equipes`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EquipeDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST3(dto: EquipeDto): __Observable<EquipeDto> {
    return this.createUsingPOST3Response(dto).pipe(
      __map(_r => _r.body as EquipeDto)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET3Response(id: number): __Observable<__StrictHttpResponse<EquipeDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/equipes/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EquipeDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET3(id: number): __Observable<EquipeDto> {
    return this.findByIdUsingGET3Response(id).pipe(
      __map(_r => _r.body as EquipeDto)
    );
  }

  /**
   * update
   * @param params The `EquipeControllerService.UpdateUsingPUT3Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT3Response(params: EquipeControllerService.UpdateUsingPUT3Params): __Observable<__StrictHttpResponse<EquipeDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/equipes/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EquipeDto>;
      })
    );
  }
  /**
   * update
   * @param params The `EquipeControllerService.UpdateUsingPUT3Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT3(params: EquipeControllerService.UpdateUsingPUT3Params): __Observable<EquipeDto> {
    return this.updateUsingPUT3Response(params).pipe(
      __map(_r => _r.body as EquipeDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE3Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/equipes/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE3(id: number): __Observable<null> {
    return this.deleteUsingDELETE3Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `EquipeControllerService.PatchUsingPATCH3Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH3Response(params: EquipeControllerService.PatchUsingPATCH3Params): __Observable<__StrictHttpResponse<EquipeDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/equipes/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EquipeDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `EquipeControllerService.PatchUsingPATCH3Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH3(params: EquipeControllerService.PatchUsingPATCH3Params): __Observable<EquipeDto> {
    return this.patchUsingPATCH3Response(params).pipe(
      __map(_r => _r.body as EquipeDto)
    );
  }
}

module EquipeControllerService {

  /**
   * Parameters for updateUsingPUT3
   */
  export interface UpdateUsingPUT3Params {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: EquipeDto;
  }

  /**
   * Parameters for patchUsingPATCH3
   */
  export interface PatchUsingPATCH3Params {

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

export { EquipeControllerService }
