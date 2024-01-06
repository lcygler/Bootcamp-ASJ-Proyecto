import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdenesFormComponent } from './ordenes-form.component';

describe('OrdenesFormComponent', () => {
  let component: OrdenesFormComponent;
  let fixture: ComponentFixture<OrdenesFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrdenesFormComponent]
    });
    fixture = TestBed.createComponent(OrdenesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
