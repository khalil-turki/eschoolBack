/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableAdresseDto } from '../models/iterable-adresse-dto';
import { AdresseDto } from '../models/adresse-dto';
import { JsonMergePatch } from '../models/json-merge-patch';

/**
 * Adresse Controller
 */
@Injectable({
  providedIn: 'root',
})
class AdresseControllerService extends __BaseService {
  static readonly findAllUsingGETPath = '/adresses';
  static readonly createUsingPOSTPath = '/adresses';
  static readonly findByIdUsingGETPath = '/adresses/{id}';
  static readonly updateUsingPUTPath = '/adresses/{id}';
  static readonly deleteUsingDELETEPath = '/adresses/{id}';
  static readonly patchUsingPATCHPath = '/adresses/{id}';

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
  findAllUsingGETResponse(): __Observable<__StrictHttpResponse<IterableAdresseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/adresses`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<IterableAdresseDto>;
      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET(): __Observable<IterableAdresseDto> {
    return this.findAllUsingGETResponse().pipe(
      __map(_r => _r.body as IterableAdresseDto)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOSTResponse(dto: AdresseDto): __Observable<__StrictHttpResponse<AdresseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/adresses`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdresseDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST(dto: AdresseDto): __Observable<AdresseDto> {
    return this.createUsingPOSTResponse(dto).pipe(
      __map(_r => _r.body as AdresseDto)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGETResponse(id: number): __Observable<__StrictHttpResponse<AdresseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/adresses/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdresseDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET(id: number): __Observable<AdresseDto> {
    return this.findByIdUsingGETResponse(id).pipe(
      __map(_r => _r.body as AdresseDto)
    );
  }

  /**
   * update
   * @param params The `AdresseControllerService.UpdateUsingPUTParams` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUTResponse(params: AdresseControllerService.UpdateUsingPUTParams): __Observable<__StrictHttpResponse<AdresseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/adresses/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdresseDto>;
      })
    );
  }
  /**
   * update
   * @param params The `AdresseControllerService.UpdateUsingPUTParams` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT(params: AdresseControllerService.UpdateUsingPUTParams): __Observable<AdresseDto> {
    return this.updateUsingPUTResponse(params).pipe(
      __map(_r => _r.body as AdresseDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/adresses/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE(id: number): __Observable<null> {
    return this.deleteUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `AdresseControllerService.PatchUsingPATCHParams` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCHResponse(params: AdresseControllerService.PatchUsingPATCHParams): __Observable<__StrictHttpResponse<AdresseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/adresses/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdresseDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `AdresseControllerService.PatchUsingPATCHParams` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH(params: AdresseControllerService.PatchUsingPATCHParams): __Observable<AdresseDto> {
    return this.patchUsingPATCHResponse(params).pipe(
      __map(_r => _r.body as AdresseDto)
    );
  }
}

module AdresseControllerService {

  /**
   * Parameters for updateUsingPUT
   */
  export interface UpdateUsingPUTParams {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: AdresseDto;
  }

  /**
   * Parameters for patchUsingPATCH
   */
  export interface PatchUsingPATCHParams {

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

export { AdresseControllerService }
