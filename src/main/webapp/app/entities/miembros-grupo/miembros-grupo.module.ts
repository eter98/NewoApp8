import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp8SharedModule } from 'app/shared';
import {
  MiembrosGrupoComponent,
  MiembrosGrupoDetailComponent,
  MiembrosGrupoUpdateComponent,
  MiembrosGrupoDeletePopupComponent,
  MiembrosGrupoDeleteDialogComponent,
  miembrosGrupoRoute,
  miembrosGrupoPopupRoute
} from './';

const ENTITY_STATES = [...miembrosGrupoRoute, ...miembrosGrupoPopupRoute];

@NgModule({
  imports: [NewoApp8SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MiembrosGrupoComponent,
    MiembrosGrupoDetailComponent,
    MiembrosGrupoUpdateComponent,
    MiembrosGrupoDeleteDialogComponent,
    MiembrosGrupoDeletePopupComponent
  ],
  entryComponents: [
    MiembrosGrupoComponent,
    MiembrosGrupoUpdateComponent,
    MiembrosGrupoDeleteDialogComponent,
    MiembrosGrupoDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp8MiembrosGrupoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
