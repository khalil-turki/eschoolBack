/* tslint:disable */
import { NgModule, ModuleWithProviders } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationInterface } from './api-configuration';

import { AdresseControllerService } from './services/adresse-controller.service';
import { ClasseControllerService } from './services/classe-controller.service';
import { EcoleControllerService } from './services/ecole-controller.service';
import { EquipeControllerService } from './services/equipe-controller.service';
import { BasicErrorControllerService } from './services/basic-error-controller.service';
import { EtudiantControllerService } from './services/etudiant-controller.service';
import { TwillioControllerService } from './services/twillio-controller.service';
import { ParentControllerService } from './services/parent-controller.service';
import { PayementControllerService } from './services/payement-controller.service';
import { ProfesseurControllerService } from './services/professeur-controller.service';
import { AuthenticationControllerService } from './services/authentication-controller.service';

/**
 * Provider for all Api services, plus ApiConfiguration
 */
@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [
    ApiConfiguration,
    AdresseControllerService,
    ClasseControllerService,
    EcoleControllerService,
    EquipeControllerService,
    BasicErrorControllerService,
    EtudiantControllerService,
    TwillioControllerService,
    ParentControllerService,
    PayementControllerService,
    ProfesseurControllerService,
    AuthenticationControllerService
  ],
})
export class ApiModule {
  static forRoot(customParams: ApiConfigurationInterface): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: {rootUrl: customParams.rootUrl}
        }
      ]
    }
  }
}
