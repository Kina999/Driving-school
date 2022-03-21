import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorLicenceComponent } from './instructor-licence.component';

describe('InstructorLicenceComponent', () => {
  let component: InstructorLicenceComponent;
  let fixture: ComponentFixture<InstructorLicenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorLicenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorLicenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
