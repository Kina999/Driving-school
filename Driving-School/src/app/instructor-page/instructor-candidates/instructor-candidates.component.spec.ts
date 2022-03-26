import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorCandidatesComponent } from './instructor-candidates.component';

describe('InstructorCandidatesComponent', () => {
  let component: InstructorCandidatesComponent;
  let fixture: ComponentFixture<InstructorCandidatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorCandidatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorCandidatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
