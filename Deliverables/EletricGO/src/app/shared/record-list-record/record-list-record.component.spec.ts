import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordListRecordComponent } from './record-list-record.component';

describe('RecordListRecordComponent', () => {
  let component: RecordListRecordComponent;
  let fixture: ComponentFixture<RecordListRecordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecordListRecordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecordListRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
