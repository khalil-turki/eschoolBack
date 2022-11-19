/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableParentDto } from '../models/iterable-parent-dto';
import { ParentDto } from '../models/parent-dto';
import { JsonMergePatch } from '../models/json-merge-patch';

/**
 * Parent Controller
 */
@Injectable({
  providedIn: 'root',
})
class ParentControllerService extends __BaseService {
  static readonly findAllUsingGET5Path = '/parents';
  static readonly createUsingPOST5Path = '/parents';
  static readonly findByIdUsingGET5Path = '/parents/{id}';
  static readonly updateUsingPUT5Path = '/parents/{id}';
  static readonly deleteUsingDELETE5Path = '/parents/{id}';
  static readonly patchUsingPATCH5Path = '/parents/{id}';

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
  findAllUsingGET5Response(): __Observable<__StrictHttpResponse<IterableParentDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/parents`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<IterableParentDto>;
      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET5(): __Observable<IterableParentDto> {
    return this.findAllUsingGET5Response().pipe(
      __map(_r => _r.body as IterableParentDto)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST5Response(dto: ParentDto): __Observable<__StrictHttpResponse<ParentDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/parents`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ParentDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST5(dto: ParentDto): __Observable<ParentDto> {
    return this.createUsingPOST5Response(dto).pipe(
      __map(_r => _r.body as ParentDto)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET5Response(id: number): __Observable<__StrictHttpResponse<ParentDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/parents/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ParentDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET5(id: number): __Observable<ParentDto> {
    return this.findByIdUsingGET5Response(id).pipe(
      __map(_r => _r.body as ParentDto)
    );
  }

  /**
   * update
   * @param params The `ParentControllerService.UpdateUsingPUT5Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT5Response(params: ParentControllerService.UpdateUsingPUT5Params): __Observable<__StrictHttpResponse<ParentDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/parents/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ParentDto>;
      })
    );
  }
  /**
   * update
   * @param params The `ParentControllerService.UpdateUsingPUT5Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT5(params: ParentControllerService.UpdateUsingPUT5Params): __Observable<ParentDto> {
    return this.updateUsingPUT5Response(params).pipe(
      __map(_r => _r.body as ParentDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE5Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/parents/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE5(id: number): __Observable<null> {
    return this.deleteUsingDELETE5Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `ParentControllerService.PatchUsingPATCH5Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH5Response(params: ParentControllerService.PatchUsingPATCH5Params): __Observable<__StrictHttpResponse<ParentDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/parents/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ParentDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `ParentControllerService.PatchUsingPATCH5Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH5(params: ParentControllerService.PatchUsingPATCH5Params): __Observable<ParentDto> {
    return this.patchUsingPATCH5Response(params).pipe(
      __map(_r => _r.body as ParentDto)
    );
  }
}

module ParentControllerService {

  /**
   * Parameters for updateUsingPUT5
   */
  export interface UpdateUsingPUT5Params {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: ParentDto;
  }

  /**
   * Parameters for patchUsingPATCH5
   */
  export interface PatchUsingPATCH5Params {

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

export { ParentControllerService }
