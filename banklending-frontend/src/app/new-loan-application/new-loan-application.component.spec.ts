import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewLoanApplicationComponent } from './new-loan-application.component';

describe('NewLoanApplicationComponent', () => {
  let component: NewLoanApplicationComponent;
  let fixture: ComponentFixture<NewLoanApplicationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewLoanApplicationComponent]
    });
    fixture = TestBed.createComponent(NewLoanApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
