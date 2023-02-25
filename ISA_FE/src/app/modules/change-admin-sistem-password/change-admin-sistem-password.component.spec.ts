import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeAdminSistemPasswordComponent } from './change-admin-sistem-password.component';

describe('ChangeAdminSistemPasswordComponent', () => {
  let component: ChangeAdminSistemPasswordComponent;
  let fixture: ComponentFixture<ChangeAdminSistemPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeAdminSistemPasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeAdminSistemPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
