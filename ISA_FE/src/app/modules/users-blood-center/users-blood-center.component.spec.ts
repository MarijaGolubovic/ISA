import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersBloodCenterComponent } from './users-blood-center.component';

describe('UsersBloodCenterComponent', () => {
  let component: UsersBloodCenterComponent;
  let fixture: ComponentFixture<UsersBloodCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersBloodCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersBloodCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
