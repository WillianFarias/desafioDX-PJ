import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntegranteFormComponent } from './integrante-form.component';

describe('IntegranteFormComponent', () => {
  let component: IntegranteFormComponent;
  let fixture: ComponentFixture<IntegranteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IntegranteFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IntegranteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
