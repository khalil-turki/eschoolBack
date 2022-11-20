/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';


/**
 * Photo Controller
 */
@Injectable({
  providedIn: 'root',
})
class PhotoControllerService extends __BaseService {
  static readonly savePhotoUsingPOSTPath = '/eschoolBack/save/{id}/{title}/{context}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * savePhoto
   * @param params The `PhotoControllerService.SavePhotoUsingPOSTParams` containing the following parameters:
   *
   * - `title`: title
   *
   * - `id`: id
   *
   * - `context`: context
   *
   * - `file`:
   *
   * @return OK
   */
  savePhotoUsingPOSTResponse(params: PhotoControllerService.SavePhotoUsingPOSTParams): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let __formData = new FormData();
    __body = __formData;



    if (params.file != null) { __formData.append('file', params.file as string | Blob);}
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/eschoolBack/save/${encodeURIComponent(String(params.id))}/${encodeURIComponent(String(params.title))}/${encodeURIComponent(String(params.context))}`,
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
   * savePhoto
   * @param params The `PhotoControllerService.SavePhotoUsingPOSTParams` containing the following parameters:
   *
   * - `title`: title
   *
   * - `id`: id
   *
   * - `context`: context
   *
   * - `file`:
   *
   * @return OK
   */
  savePhotoUsingPOST(params: PhotoControllerService.SavePhotoUsingPOSTParams): __Observable<{}> {
    return this.savePhotoUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as {})
    );
  }
}

module PhotoControllerService {

  /**
   * Parameters for savePhotoUsingPOST
   */
  export interface SavePhotoUsingPOSTParams {

    /**
     * title
     */
    title: string;

    /**
     * id
     */
    id: number;

    /**
     * context
     */
    context: string;
    file?: Blob;
  }
}

export { PhotoControllerService }
