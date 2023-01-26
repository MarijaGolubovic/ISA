import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FutureAppointmentsBBComponent } from './future-appointments-bb.component';

describe('FutureAppointmentsBBComponent', () => {
  let component: FutureAppointmentsBBComponent;
  let fixture: ComponentFixture<FutureAppointmentsBBComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FutureAppointmentsBBComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FutureAppointmentsBBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
