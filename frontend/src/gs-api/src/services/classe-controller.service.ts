/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableClasseDto } from '../models/iterable-classe-dto';
import { ClasseDto } from '../models/classe-dto';
import { JsonMergePatch } from '../models/json-merge-patch';

/**
 * Classe Controller
 */
@Injectable({
  providedIn: 'root',
})
class ClasseControllerService extends __BaseService {
  static readonly findAllUsingGET1Path = '/classes';
  static readonly createUsingPOST1Path = '/classes';
  static readonly findByIdUsingGET1Path = '/classes/{id}';
  static readonly updateUsingPUT1Path = '/classes/{id}';
  static readonly deleteUsingDELETE1Path = '/classes/{id}';
  static readonly patchUsingPATCH1Path = '/classes/{id}';

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
  findAllUsingGET1Response(): __Observable<__StrictHttpResponse<Array<ClasseDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/classes`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ClasseDto>>;

      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET1(): __Observable<Array<ClasseDto>> {
    return this.findAllUsingGET1Response().pipe(
      __map(_r => _r.body as Array<ClasseDto>)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST1Response(dto: ClasseDto): __Observable<__StrictHttpResponse<ClasseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/classes`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClasseDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST1(dto: ClasseDto): __Observable<ClasseDto> {
    return this.createUsingPOST1Response(dto).pipe(
      __map(_r => _r.body as ClasseDto)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET1Response(id: number): __Observable<__StrictHttpResponse<ClasseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/classes/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClasseDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET1(id: number): __Observable<ClasseDto> {
    return this.findByIdUsingGET1Response(id).pipe(
      __map(_r => _r.body as ClasseDto)
    );
  }

  /**
   * update
   * @param params The `ClasseControllerService.UpdateUsingPUT1Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT1Response(params: ClasseControllerService.UpdateUsingPUT1Params): __Observable<__StrictHttpResponse<ClasseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/classes/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClasseDto>;
      })
    );
  }
  /**
   * update
   * @param params The `ClasseControllerService.UpdateUsingPUT1Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT1(params: ClasseControllerService.UpdateUsingPUT1Params): __Observable<ClasseDto> {
    return this.updateUsingPUT1Response(params).pipe(
      __map(_r => _r.body as ClasseDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE1Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/classes/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE1(id: number): __Observable<null> {
    return this.deleteUsingDELETE1Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `ClasseControllerService.PatchUsingPATCH1Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH1Response(params: ClasseControllerService.PatchUsingPATCH1Params): __Observable<__StrictHttpResponse<ClasseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/classes/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClasseDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `ClasseControllerService.PatchUsingPATCH1Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH1(params: ClasseControllerService.PatchUsingPATCH1Params): __Observable<ClasseDto> {
    return this.patchUsingPATCH1Response(params).pipe(
      __map(_r => _r.body as ClasseDto)
    );
  }
}

module ClasseControllerService {

  /**
   * Parameters for updateUsingPUT1
   */
  export interface UpdateUsingPUT1Params {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: ClasseDto;
  }

  /**
   * Parameters for patchUsingPATCH1
   */
  export interface PatchUsingPATCH1Params {

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

export { ClasseControllerService }
