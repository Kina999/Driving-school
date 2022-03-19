import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateAccountComponent } from './candidate-account.component';

describe('CandidateAccountComponent', () => {
  let component: CandidateAccountComponent;
  let fixture: ComponentFixture<CandidateAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidateAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
