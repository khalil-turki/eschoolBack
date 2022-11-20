import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';

import { CoreCommonModule } from '@core/common.module';
import { ContentHeaderModule } from 'app/layout/components/content-header/content-header.module';

import { KbModule } from './kb/kb.module';
import { BlogModule } from './blog/blog.module';
import { Ng2FlatpickrModule } from 'ng2-flatpickr';
import { ProfileModule } from './profile/profile.module';
import { PricingModule } from './pricing/pricing.module';
import { FaqModule } from 'app/main/pages/faq/faq.module';
import { AccountSettingsModule } from './account-settings/account-settings.module';
import { AuthenticationModule } from './authentication/authentication.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { ClassesComponent } from './classes/classes.component';
import { EcoleComponent } from './ecole/ecole-list/ecole.component';
import { EtudiantsComponent } from './etudiants/etudiants.component';
import { PaymentSessionComponent } from './payment-session/payment-session.component';
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {SweetAlert2Module} from "@sweetalert2/ngx-sweetalert2";
import {CoreSidebarModule} from "../../../@core/components";
import {CardSnippetModule} from "../../../@core/components/card-snippet/card-snippet.module";
import {CsvModule} from "@ctrl/ngx-csv";

@NgModule({
  declarations: [
    ClassesComponent,
    EcoleComponent,
    EtudiantsComponent,
    PaymentSessionComponent

  ],
    imports: [
        CommonModule,
        CoreCommonModule,
        ContentHeaderModule,
        NgbModule,
        NgSelectModule,
        FormsModule,
        AuthenticationModule,
        MiscellaneousModule,
        Ng2FlatpickrModule,
        PricingModule,
        BlogModule,
        ProfileModule,
        KbModule,
        FaqModule,
        AccountSettingsModule,
        NgxDatatableModule,
        SweetAlert2Module,
        CoreSidebarModule,
        CardSnippetModule,
        CsvModule
    ],

  providers: []
})
export class PagesModule {}
