/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { IterableEtudiantDto } from '../models/iterable-etudiant-dto';
import { EtudiantDto } from '../models/etudiant-dto';
import { JsonMergePatch } from '../models/json-merge-patch';
import {ClasseDto} from "../models/classe-dto";

/**
 * Etudiant Controller
 */
@Injectable({
  providedIn: 'root',
})
class EtudiantControllerService extends __BaseService {
  static readonly findAllUsingGET4Path = '/etudiants';
  static readonly createUsingPOST4Path = '/etudiants';
  static readonly assignEtudiantToClasseUsingPOSTPath = '/etudiants/affecttoclasse/{etudiantid}/{classeid}';
  static readonly assignEtudiantToEcoleUsingPOSTPath = '/etudiants/affecttoecole/{etudiantid}/{ecoleid}';
  static readonly removeEtudiantFromClasseUsingPOSTPath = '/etudiants/desafectfromclasse/{etudiantid}/{classeid}';
  static readonly removeEtudiantFromEcoleUsingPOSTPath = '/etudiants/desafectfromecole/{etudiantid}/{ecoleid}';
  static readonly findByIdUsingGET4Path = '/etudiants/{id}';
  static readonly updateUsingPUT4Path = '/etudiants/{id}';
  static readonly deleteUsingDELETE4Path = '/etudiants/{id}';
  static readonly patchUsingPATCH4Path = '/etudiants/{id}';

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
  findAllUsingGET4Response():__Observable<__StrictHttpResponse<Array<EtudiantDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/etudiants`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<EtudiantDto>>;
      })
    );
  }
  /**
   * findAll
   * @return OK
   */
  findAllUsingGET4():__Observable<Array<EtudiantDto>> {
    return this.findAllUsingGET4Response().pipe(
        __map(_r => _r.body as Array<EtudiantDto>)
    );
  }

  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST4Response(dto: EtudiantDto): __Observable<__StrictHttpResponse<EtudiantDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = dto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/etudiants`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EtudiantDto>;
      })
    );
  }
  /**
   * create
   * @param dto dto
   * @return OK
   */
  createUsingPOST4(dto: EtudiantDto): __Observable<EtudiantDto> {
    return this.createUsingPOST4Response(dto).pipe(
      __map(_r => _r.body as EtudiantDto)
    );
  }

  /**
   * assignEtudiantToClasse
   * @param params The `EtudiantControllerService.AssignEtudiantToClasseUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `classeid`: classeid
   */
  assignEtudiantToClasseUsingPOSTResponse(params: EtudiantControllerService.AssignEtudiantToClasseUsingPOSTParams): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/etudiants/affecttoclasse/${encodeURIComponent(String(params.etudiantid))}/${encodeURIComponent(String(params.classeid))}`,
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
   * assignEtudiantToClasse
   * @param params The `EtudiantControllerService.AssignEtudiantToClasseUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `classeid`: classeid
   */
  assignEtudiantToClasseUsingPOST(params: EtudiantControllerService.AssignEtudiantToClasseUsingPOSTParams): __Observable<null> {
    return this.assignEtudiantToClasseUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * assignEtudiantToEcole
   * @param params The `EtudiantControllerService.AssignEtudiantToEcoleUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `ecoleid`: ecoleid
   */
  assignEtudiantToEcoleUsingPOSTResponse(params: EtudiantControllerService.AssignEtudiantToEcoleUsingPOSTParams): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/etudiants/affecttoecole/${encodeURIComponent(String(params.etudiantid))}/${encodeURIComponent(String(params.ecoleid))}`,
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
   * assignEtudiantToEcole
   * @param params The `EtudiantControllerService.AssignEtudiantToEcoleUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `ecoleid`: ecoleid
   */
  assignEtudiantToEcoleUsingPOST(params: EtudiantControllerService.AssignEtudiantToEcoleUsingPOSTParams): __Observable<null> {
    return this.assignEtudiantToEcoleUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * removeEtudiantFromClasse
   * @param params The `EtudiantControllerService.RemoveEtudiantFromClasseUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `classeid`: classeid
   */
  removeEtudiantFromClasseUsingPOSTResponse(params: EtudiantControllerService.RemoveEtudiantFromClasseUsingPOSTParams): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/etudiants/desafectfromclasse/${encodeURIComponent(String(params.etudiantid))}/${encodeURIComponent(String(params.classeid))}`,
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
   * removeEtudiantFromClasse
   * @param params The `EtudiantControllerService.RemoveEtudiantFromClasseUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `classeid`: classeid
   */
  removeEtudiantFromClasseUsingPOST(params: EtudiantControllerService.RemoveEtudiantFromClasseUsingPOSTParams): __Observable<null> {
    return this.removeEtudiantFromClasseUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * removeEtudiantFromEcole
   * @param params The `EtudiantControllerService.RemoveEtudiantFromEcoleUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `ecoleid`: ecoleid
   */
  removeEtudiantFromEcoleUsingPOSTResponse(params: EtudiantControllerService.RemoveEtudiantFromEcoleUsingPOSTParams): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/etudiants/desafectfromecole/${encodeURIComponent(String(params.etudiantid))}/${encodeURIComponent(String(params.ecoleid))}`,
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
   * removeEtudiantFromEcole
   * @param params The `EtudiantControllerService.RemoveEtudiantFromEcoleUsingPOSTParams` containing the following parameters:
   *
   * - `etudiantid`: etudiantid
   *
   * - `ecoleid`: ecoleid
   */
  removeEtudiantFromEcoleUsingPOST(params: EtudiantControllerService.RemoveEtudiantFromEcoleUsingPOSTParams): __Observable<null> {
    return this.removeEtudiantFromEcoleUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET4Response(id: number): __Observable<__StrictHttpResponse<EtudiantDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/etudiants/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EtudiantDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET4(id: number): __Observable<EtudiantDto> {
    return this.findByIdUsingGET4Response(id).pipe(
      __map(_r => _r.body as EtudiantDto)
    );
  }

  /**
   * update
   * @param params The `EtudiantControllerService.UpdateUsingPUT4Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT4Response(params: EtudiantControllerService.UpdateUsingPUT4Params): __Observable<__StrictHttpResponse<EtudiantDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    __body = params.dto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/etudiants/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EtudiantDto>;
      })
    );
  }
  /**
   * update
   * @param params The `EtudiantControllerService.UpdateUsingPUT4Params` containing the following parameters:
   *
   * - `id`: id
   *
   * - `dto`: dto
   *
   * @return OK
   */
  updateUsingPUT4(params: EtudiantControllerService.UpdateUsingPUT4Params): __Observable<EtudiantDto> {
    return this.updateUsingPUT4Response(params).pipe(
      __map(_r => _r.body as EtudiantDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE4Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/etudiants/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE4(id: number): __Observable<null> {
    return this.deleteUsingDELETE4Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * patch
   * @param params The `EtudiantControllerService.PatchUsingPATCH4Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH4Response(params: EtudiantControllerService.PatchUsingPATCH4Params): __Observable<__StrictHttpResponse<EtudiantDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.patch;

    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/etudiants/${encodeURIComponent(String(params.id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EtudiantDto>;
      })
    );
  }
  /**
   * patch
   * @param params The `EtudiantControllerService.PatchUsingPATCH4Params` containing the following parameters:
   *
   * - `patch`: patch
   *
   * - `id`: id
   *
   * @return OK
   */
  patchUsingPATCH4(params: EtudiantControllerService.PatchUsingPATCH4Params): __Observable<EtudiantDto> {
    return this.patchUsingPATCH4Response(params).pipe(
      __map(_r => _r.body as EtudiantDto)
    );
  }
}

module EtudiantControllerService {

  /**
   * Parameters for assignEtudiantToClasseUsingPOST
   */
  export interface AssignEtudiantToClasseUsingPOSTParams {

    /**
     * etudiantid
     */
    etudiantid: number;

    /**
     * classeid
     */
    classeid: number;
  }

  /**
   * Parameters for assignEtudiantToEcoleUsingPOST
   */
  export interface AssignEtudiantToEcoleUsingPOSTParams {

    /**
     * etudiantid
     */
    etudiantid: number;

    /**
     * ecoleid
     */
    ecoleid: number;
  }

  /**
   * Parameters for removeEtudiantFromClasseUsingPOST
   */
  export interface RemoveEtudiantFromClasseUsingPOSTParams {

    /**
     * etudiantid
     */
    etudiantid: number;

    /**
     * classeid
     */
    classeid: number;
  }

  /**
   * Parameters for removeEtudiantFromEcoleUsingPOST
   */
  export interface RemoveEtudiantFromEcoleUsingPOSTParams {

    /**
     * etudiantid
     */
    etudiantid: number;

    /**
     * ecoleid
     */
    ecoleid: number;
  }

  /**
   * Parameters for updateUsingPUT4
   */
  export interface UpdateUsingPUT4Params {

    /**
     * id
     */
    id: number;

    /**
     * dto
     */
    dto: EtudiantDto;
  }

  /**
   * Parameters for patchUsingPATCH4
   */
  export interface PatchUsingPATCH4Params {

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

export { EtudiantControllerService }
