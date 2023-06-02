import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PenalsComponent } from './penals.component';

describe('PenalsComponent', () => {
  let component: PenalsComponent;
  let fixture: ComponentFixture<PenalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PenalsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PenalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
