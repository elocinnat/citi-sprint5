import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GainerLoserRowComponent } from './gainer-loser-row.component';

describe('GainerLoserRowComponent', () => {
  let component: GainerLoserRowComponent;
  let fixture: ComponentFixture<GainerLoserRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GainerLoserRowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GainerLoserRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
