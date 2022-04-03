import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateTerminsComponent } from './candidate-termins.component';

describe('CandidateTerminsComponent', () => {
  let component: CandidateTerminsComponent;
  let fixture: ComponentFixture<CandidateTerminsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidateTerminsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateTerminsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
