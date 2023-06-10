import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VahiclesCoordinatesComponent } from './vahicles-coordinates.component';

describe('VahiclesCoordinatesComponent', () => {
  let component: VahiclesCoordinatesComponent;
  let fixture: ComponentFixture<VahiclesCoordinatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VahiclesCoordinatesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VahiclesCoordinatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
