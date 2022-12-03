import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EcoleNewComponent } from './ecole-new.component';

describe('EcoleNewComponent', () => {
  let component: EcoleNewComponent;
  let fixture: ComponentFixture<EcoleNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EcoleNewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EcoleNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
