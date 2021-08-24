import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TradeStatusComponent } from './trade-status.component';

describe('TradeStatusComponent', () => {
  let component: TradeStatusComponent;
  let fixture: ComponentFixture<TradeStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TradeStatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TradeStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
