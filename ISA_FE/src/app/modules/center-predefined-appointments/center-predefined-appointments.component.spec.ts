import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterPredefinedAppointmentsComponent } from './center-predefined-appointments.component';

describe('CenterPredefinedAppointmentsComponent', () => {
  let component: CenterPredefinedAppointmentsComponent;
  let fixture: ComponentFixture<CenterPredefinedAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CenterPredefinedAppointmentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CenterPredefinedAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
