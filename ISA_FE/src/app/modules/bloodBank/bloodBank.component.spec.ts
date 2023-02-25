import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EditBloodBankComponent } from './bloodBank.component';



describe('EditUserComponent', () => {
  let component: EditBloodBankComponent;
  let fixture: ComponentFixture<EditBloodBankComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditBloodBankComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditBloodBankComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
