import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';

import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {FakeDbService} from '@fake-db/fake-db.service';

import 'hammerjs';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ToastrModule} from 'ngx-toastr';
import {TranslateModule} from '@ngx-translate/core';
import {ContextMenuModule} from '@ctrl/ngx-rightclick';

import {CoreModule} from '@core/core.module';
import {CoreCommonModule} from '@core/common.module';
import {CoreSidebarModule, CoreThemeCustomizerModule} from '@core/components';
import {CardSnippetModule} from '@core/components/card-snippet/card-snippet.module';

import {coreConfig} from 'app/app-config';
import {AuthGuard} from 'app/auth/helpers/auth.guards';
import {JwtInterceptor, ErrorInterceptor} from 'app/auth/helpers';
import {AppComponent} from 'app/app.component';
import {LayoutModule} from 'app/layout/layout.module';
import {ContentHeaderModule} from 'app/layout/components/content-header/content-header.module';

import {ContextMenuComponent} from 'app/main/extensions/context-menu/context-menu.component';
import {
    AnimatedCustomContextMenuComponent
} from './main/extensions/context-menu/custom-context-menu/animated-custom-context-menu/animated-custom-context-menu.component';
import {
    BasicCustomContextMenuComponent
} from './main/extensions/context-menu/custom-context-menu/basic-custom-context-menu/basic-custom-context-menu.component';
import {
    SubMenuCustomContextMenuComponent
} from './main/extensions/context-menu/custom-context-menu/sub-menu-custom-context-menu/sub-menu-custom-context-menu.component';
import {EcoleComponent} from "./main/pages/ecole/ecole-list/ecole.component";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {DatatablesService} from "./main/tables/datatables/datatables.service";
import {InvoiceComponent} from "./main/pages/invoice/invoice.component";
import {PaymentSessionComponent} from "./main/pages/payment-session/payment-session.component";
import {EcoleNewComponent} from "./main/pages/ecole/ecole-new/ecole-new.component";
import {NgSelectModule} from "@ng-select/ng-select";
import {EcoleEditComponent} from "./main/pages/ecole/ecole-edit/ecole-edit.component";
import {ClasseListComponent} from "./main/pages/classe/classe-list/classe-list/classe-list.component";
import {NouveauClasseComponent} from "./main/pages/classe/nouveau-classe/nouveau-classe.component";
import {
    NouveauEtudiantComponent
} from "./main/pages/etudiant/nouveau-etudiant/nouveau-etudiant/nouveau-etudiant.component";
import {PageEtudiantsComponent} from "./main/pages/etudiant/etudiant-list/etudiant-list/page-etudiants.component";
import {StattComponent} from "./main/pages/statt/statt.component";
import { RxStompService } from './layout/components/navbar/navbar-notification/rx-stomp.service';
import {rxStompServiceFactory} from "./layout/components/navbar/navbar-notification/rx-stomp-service-factory";

const appRoutes: Routes = [
    {
        path: 'dashboard',
        loadChildren: () => import('./main/dashboard/dashboard.module').then(m => m.DashboardModule)
    },
    {
        path: 'apps',
        loadChildren: () => import('./main/apps/apps.module').then(m => m.AppsModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'pages',
        loadChildren: () => import('./main/pages/pages.module').then(m => m.PagesModule)
    },
    {
        path: 'classes',
        component: ClasseListComponent,

    },

    {
        path: 'invoices',
        component: InvoiceComponent,

    },
    {
        path: 'stat',
        component: StattComponent,

    },
    {
        path: 'ecoles/listecoles',
        component: EcoleComponent,
    },
    {
        path: 'invoices',
        component: InvoiceComponent,
        data: {animation: 'InvoiceComponent'}

    },
    {
        path: 'payement',
        component: PaymentSessionComponent,

    },
    {
        path: 'ecoles/newecole',
        component: EcoleNewComponent,


    },
    {
        path: 'ecoles/newecole/:id',
        component: EcoleEditComponent,


    },

    {
        path: 'etudiants',
        component: PageEtudiantsComponent,
    },

    {
        path: 'etudiants/:idClasse',
        component: PageEtudiantsComponent,
    },

    {
        path: 'nvEtudiant',
        component: NouveauEtudiantComponent,
    },
    {
        path: 'nvEtudiant/:idEtudiant',
        component: NouveauEtudiantComponent,
    },

    {
        path: 'classes',
        component: ClasseListComponent,
    },

    {
        path: 'nvClasse',
        component: NouveauClasseComponent,
    },

    {
        path: 'nvClasse/:idClasse',
        component: NouveauClasseComponent,
    },


    {
        path: 'ui',
        loadChildren: () => import('./main/ui/ui.module').then(m => m.UIModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'components',
        loadChildren: () => import('./main/components/components.module').then(m => m.ComponentsModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'extensions',
        loadChildren: () => import('./main/extensions/extensions.module').then(m => m.ExtensionsModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'forms',
        loadChildren: () => import('./main/forms/forms.module').then(m => m.FormsModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'tables',
        loadChildren: () => import('./main/tables/tables.module').then(m => m.TablesModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'charts-and-maps',
        loadChildren: () => import('./main/charts-and-maps/charts-and-maps.module').then(m => m.ChartsAndMapsModule),
        canActivate: [AuthGuard]
    },
    {
        path: '',
        redirectTo: '/dashboard/ecommerce',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/pages/miscellaneous/error' //Error 404 - Page not found
    }
];

@NgModule({
    declarations: [
        AppComponent,
        ContextMenuComponent,
        BasicCustomContextMenuComponent,
        AnimatedCustomContextMenuComponent,
        SubMenuCustomContextMenuComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        HttpClientInMemoryWebApiModule.forRoot(FakeDbService, {
            delay: 0,
            passThruUnknownUrl: true
        }),
        RouterModule.forRoot(appRoutes, {
            scrollPositionRestoration: 'enabled',
            relativeLinkResolution: 'legacy'
        }),
        NgbModule,
        ToastrModule.forRoot(),
        TranslateModule.forRoot(),
        ContextMenuModule,
        CoreModule.forRoot(coreConfig),
        CoreCommonModule,
        CoreSidebarModule,
        CoreThemeCustomizerModule,
        CardSnippetModule,
        LayoutModule,
        ContentHeaderModule,
        NgxDatatableModule,
        NgSelectModule

    ],
    providers: [
        [DatatablesService],
        {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
        {
            provide: RxStompService,
            useFactory: rxStompServiceFactory,
        }
    ],
    bootstrap: [AppComponent],


})
export class AppModule {
}
