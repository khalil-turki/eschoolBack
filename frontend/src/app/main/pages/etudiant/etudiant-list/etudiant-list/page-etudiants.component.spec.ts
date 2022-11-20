import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEtudiantsComponent } from './page-etudiants.component';
import {beforeEach, describe, expect, it} from "jasmine";

describe('PageEtudiantsComponent', () => {
  let component: PageEtudiantsComponent;
  let fixture: ComponentFixture<PageEtudiantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageEtudiantsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PageEtudiantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
