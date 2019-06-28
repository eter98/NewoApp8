import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NewoApp8SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [NewoApp8SharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [NewoApp8SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp8SharedModule {
  static forRoot() {
    return {
      ngModule: NewoApp8SharedModule
    };
  }
}
