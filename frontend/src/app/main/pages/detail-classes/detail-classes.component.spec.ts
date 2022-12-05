import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailClassesComponent } from './detail-classes.component';
import {beforeEach, describe, expect, it} from "jasmine";

describe('DetailClassesComponent', () => {
  let component: DetailClassesComponent;
  let fixture: ComponentFixture<DetailClassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailClassesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
