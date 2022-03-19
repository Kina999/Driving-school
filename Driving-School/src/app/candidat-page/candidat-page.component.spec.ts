import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatPageComponent } from './candidat-page.component';

describe('CandidatPageComponent', () => {
  let component: CandidatPageComponent;
  let fixture: ComponentFixture<CandidatPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
