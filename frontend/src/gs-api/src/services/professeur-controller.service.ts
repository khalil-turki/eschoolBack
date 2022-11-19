/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableProfesseurDto } from '../models/iterable-professeur-dto';
import { ProfesseurDto } from '../models/professeur-dto';
import { JsonMergePatch } from '../models/json-merge-patch';

/**
 * Professeur Controller
 */
@Injectable({
  providedIn: 'root',
})
class ProfesseurControllerService extends __BaseService {
  static readonly findAllUsingGET6Path = '/professeurs';
  static readonly createUsingPOST6Path = '/professeurs';
  static readonly findByIdUsingGET6Path = '/professeurs/{id}';
  static readonly updateUsingPUT6Path = '/professeurs/{id}';
  static readonly deleteUsingDELETE6Path = '/professeurs/{id}';
  static readonly patchUsingPATCH6Path = '/professeurs/{id}';

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
  findAllUsingGET6Response(): __Observable<__StrictHttpResponse<IterableProfesseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/professeurs`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<IterableProfesseurDto>;
      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET6(): __Observable<IterableProfesseurDto> {
    return this.findAllUsingGET6Response().pipe(
      __map(_r => _r.body as IterableProfesseurDto)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST6Response(dto: ProfesseurDto): __Observable<__StrictHttpResponse<ProfesseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/professeurs`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProfesseurDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST6(dto: ProfesseurDto): __Observable<ProfesseurDto> {
    return this.createUsingPOST6Response(dto).pipe(
      __map(_r => _r.body as ProfesseurDto)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET6Response(id: number): __Observable<__StrictHttpResponse<ProfesseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/professeurs/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProfesseurDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET6(id: number): __Observable<ProfesseurDto> {
    return this.findByIdUsingGET6Response(id).pipe(
      __map(_r => _r.body as ProfesseurDto)
    );
  }

  /**
   * update
   * @param params The `ProfesseurControllerService.UpdateUsingPUT6Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT6Response(params: ProfesseurControllerService.UpdateUsingPUT6Params): __Observable<__StrictHttpResponse<ProfesseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/professeurs/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProfesseurDto>;
      })
    );
  }
  /**
   * update
   * @param params The `ProfesseurControllerService.UpdateUsingPUT6Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT6(params: ProfesseurControllerService.UpdateUsingPUT6Params): __Observable<ProfesseurDto> {
    return this.updateUsingPUT6Response(params).pipe(
      __map(_r => _r.body as ProfesseurDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE6Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/professeurs/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE6(id: number): __Observable<null> {
    return this.deleteUsingDELETE6Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `ProfesseurControllerService.PatchUsingPATCH6Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH6Response(params: ProfesseurControllerService.PatchUsingPATCH6Params): __Observable<__StrictHttpResponse<ProfesseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/professeurs/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProfesseurDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `ProfesseurControllerService.PatchUsingPATCH6Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH6(params: ProfesseurControllerService.PatchUsingPATCH6Params): __Observable<ProfesseurDto> {
    return this.patchUsingPATCH6Response(params).pipe(
      __map(_r => _r.body as ProfesseurDto)
    );
  }
}

module ProfesseurControllerService {

  /**
   * Parameters for updateUsingPUT6
   */
  export interface UpdateUsingPUT6Params {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: ProfesseurDto;
  }

  /**
   * Parameters for patchUsingPATCH6
   */
  export interface PatchUsingPATCH6Params {

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

export { ProfesseurControllerService }
