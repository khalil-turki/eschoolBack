import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EcoleEditComponent } from './ecole-edit.component';

describe('EcoleEditComponent', () => {
  let component: EcoleEditComponent;
  let fixture: ComponentFixture<EcoleEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EcoleEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EcoleEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
