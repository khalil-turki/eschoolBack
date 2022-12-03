import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { PaymentSessionComponent } from './payment-session.component';

describe('PaymentSessionComponent', () => {
  let component: PaymentSessionComponent;
  let fixture: ComponentFixture<PaymentSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentSessionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
