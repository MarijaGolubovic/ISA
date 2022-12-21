import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulingNewAppointmentComponent } from './scheduling-new-appointment.component';

describe('SchedulingNewAppointmentComponent', () => {
  let component: SchedulingNewAppointmentComponent;
  let fixture: ComponentFixture<SchedulingNewAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchedulingNewAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SchedulingNewAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
