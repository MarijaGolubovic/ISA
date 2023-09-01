import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnautorizedComponentComponent } from './unautorized-component.component';

describe('UnautorizedComponentComponent', () => {
  let component: UnautorizedComponentComponent;
  let fixture: ComponentFixture<UnautorizedComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnautorizedComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UnautorizedComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
