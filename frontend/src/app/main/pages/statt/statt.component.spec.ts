import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StattComponent } from './statt.component';

describe('StattComponent', () => {
  let component: StattComponent;
  let fixture: ComponentFixture<StattComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StattComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StattComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
