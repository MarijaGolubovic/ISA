import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendNewsToHospitalComponent } from './send-news-to-hospital.component';

describe('SendNewsToHospitalComponent', () => {
  let component: SendNewsToHospitalComponent;
  let fixture: ComponentFixture<SendNewsToHospitalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendNewsToHospitalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendNewsToHospitalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
