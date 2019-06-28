import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp8SharedModule } from 'app/shared';
import {
  TipoRecursoComponent,
  TipoRecursoDetailComponent,
  TipoRecursoUpdateComponent,
  TipoRecursoDeletePopupComponent,
  TipoRecursoDeleteDialogComponent,
  tipoRecursoRoute,
  tipoRecursoPopupRoute
} from './';

const ENTITY_STATES = [...tipoRecursoRoute, ...tipoRecursoPopupRoute];

@NgModule({
  imports: [NewoApp8SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TipoRecursoComponent,
    TipoRecursoDetailComponent,
    TipoRecursoUpdateComponent,
    TipoRecursoDeleteDialogComponent,
    TipoRecursoDeletePopupComponent
  ],
  entryComponents: [TipoRecursoComponent, TipoRecursoUpdateComponent, TipoRecursoDeleteDialogComponent, TipoRecursoDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp8TipoRecursoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
