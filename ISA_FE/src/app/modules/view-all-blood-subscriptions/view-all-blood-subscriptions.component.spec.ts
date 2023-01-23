import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllBloodSubscriptionsComponent } from './view-all-blood-subscriptions.component';

describe('ViewAllBloodSubscriptionsComponent', () => {
  let component: ViewAllBloodSubscriptionsComponent;
  let fixture: ComponentFixture<ViewAllBloodSubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAllBloodSubscriptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllBloodSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
