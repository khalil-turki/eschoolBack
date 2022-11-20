import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouveauClasseComponent } from './nouveau-classe.component';
import {beforeEach, describe, expect, it} from "jasmine";

describe('NouveauClasseComponent', () => {
  let component: NouveauClasseComponent;
  let fixture: ComponentFixture<NouveauClasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NouveauClasseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NouveauClasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
