import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodSupplyComponent } from './blood-supply.component';

describe('BloodSupplyComponent', () => {
  let component: BloodSupplyComponent;
  let fixture: ComponentFixture<BloodSupplyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodSupplyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodSupplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
