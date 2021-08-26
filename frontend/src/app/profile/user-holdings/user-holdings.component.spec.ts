import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHoldingsComponent } from './user-holdings.component';

describe('UserHoldingsComponent', () => {
  let component: UserHoldingsComponent;
  let fixture: ComponentFixture<UserHoldingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserHoldingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserHoldingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
