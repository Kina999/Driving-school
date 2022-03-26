import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatInstructorComponent } from './candidat-instructor.component';

describe('CandidatInstructorComponent', () => {
  let component: CandidatInstructorComponent;
  let fixture: ComponentFixture<CandidatInstructorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatInstructorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatInstructorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
