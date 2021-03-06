import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorHistoryComponent } from './instructor-history.component';

describe('InstructorHistoryComponent', () => {
  let component: InstructorHistoryComponent;
  let fixture: ComponentFixture<InstructorHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
