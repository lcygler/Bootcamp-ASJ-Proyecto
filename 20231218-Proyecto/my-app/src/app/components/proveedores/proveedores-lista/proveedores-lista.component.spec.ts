import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedoresListaComponent } from './proveedores-lista.component';

describe('ProveedoresListaComponent', () => {
  let component: ProveedoresListaComponent;
  let fixture: ComponentFixture<ProveedoresListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProveedoresListaComponent]
    });
    fixture = TestBed.createComponent(ProveedoresListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
