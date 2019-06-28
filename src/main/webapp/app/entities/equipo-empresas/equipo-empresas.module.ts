import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp8SharedModule } from 'app/shared';
import {
  EquipoEmpresasComponent,
  EquipoEmpresasDetailComponent,
  EquipoEmpresasUpdateComponent,
  EquipoEmpresasDeletePopupComponent,
  EquipoEmpresasDeleteDialogComponent,
  equipoEmpresasRoute,
  equipoEmpresasPopupRoute
} from './';

const ENTITY_STATES = [...equipoEmpresasRoute, ...equipoEmpresasPopupRoute];

@NgModule({
  imports: [NewoApp8SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EquipoEmpresasComponent,
    EquipoEmpresasDetailComponent,
    EquipoEmpresasUpdateComponent,
    EquipoEmpresasDeleteDialogComponent,
    EquipoEmpresasDeletePopupComponent
  ],
  entryComponents: [
    EquipoEmpresasComponent,
    EquipoEmpresasUpdateComponent,
    EquipoEmpresasDeleteDialogComponent,
    EquipoEmpresasDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp8EquipoEmpresasModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
