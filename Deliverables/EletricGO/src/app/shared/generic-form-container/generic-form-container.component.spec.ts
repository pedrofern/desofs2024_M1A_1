import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericFormContainerComponent } from './generic-form-container.component';

describe('GenericFormContainerComponent', () => {
  let component: GenericFormContainerComponent;
  let fixture: ComponentFixture<GenericFormContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenericFormContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenericFormContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
