/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';


/**
 * Payement Controller
 */
@Injectable({
  providedIn: 'root',
})
class PayementControllerService extends __BaseService {
  static readonly payUsingGETPath = '/payment/pay';
  static readonly webhookUsingPOSTPath = '/payment/webhook';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * pay
   * @return OK
   */
  payUsingGETResponse(): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/payment/pay`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<string>;
      })
    );
  }
  /**
   * pay
   * @return OK
   */
  payUsingGET(): __Observable<string> {
    return this.payUsingGETResponse().pipe(
      __map(_r => _r.body as string)
    );
  }

  /**
   * webhook
   * @param params The `PayementControllerService.WebhookUsingPOSTParams` containing the following parameters:
   *
   * - `payload`: payload
   *
   * - `Stripe-Signature`: Stripe-Signature
   *
   * @return OK
   */
  webhookUsingPOSTResponse(params: PayementControllerService.WebhookUsingPOSTParams): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.payload;
    if (params.StripeSignature != null) __headers = __headers.set('Stripe-Signature', params.StripeSignature.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/payment/webhook`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<string>;
      })
    );
  }
  /**
   * webhook
   * @param params The `PayementControllerService.WebhookUsingPOSTParams` containing the following parameters:
   *
   * - `payload`: payload
   *
   * - `Stripe-Signature`: Stripe-Signature
   *
   * @return OK
   */
  webhookUsingPOST(params: PayementControllerService.WebhookUsingPOSTParams): __Observable<string> {
    return this.webhookUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as string)
    );
  }
}

module PayementControllerService {

  /**
   * Parameters for webhookUsingPOST
   */
  export interface WebhookUsingPOSTParams {

    /**
     * payload
     */
    payload: string;

    /**
     * Stripe-Signature
     */
    StripeSignature: string;
  }
}

export { PayementControllerService }
