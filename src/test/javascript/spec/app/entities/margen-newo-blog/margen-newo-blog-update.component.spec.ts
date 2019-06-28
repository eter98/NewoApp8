/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoBlogUpdateComponent } from 'app/entities/margen-newo-blog/margen-newo-blog-update.component';
import { MargenNewoBlogService } from 'app/entities/margen-newo-blog/margen-newo-blog.service';
import { MargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';

describe('Component Tests', () => {
  describe('MargenNewoBlog Management Update Component', () => {
    let comp: MargenNewoBlogUpdateComponent;
    let fixture: ComponentFixture<MargenNewoBlogUpdateComponent>;
    let service: MargenNewoBlogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoBlogUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MargenNewoBlogUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoBlogUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoBlogService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MargenNewoBlog(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MargenNewoBlog();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
