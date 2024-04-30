import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { EventAggregatorService } from 'src/services/event-aggregator.service';
import { LoginService } from 'src/services/login.service';

import { HeaderComponent } from './header.component';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  const serviceSpy = jasmine.createSpyObj('LoginService', ['logout']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
          HttpClientTestingModule,
          MatDialogModule,
          MatSortModule,
          MatPaginatorModule,
          BrowserAnimationsModule
      ],
      providers: [{provide: MatDialogRef, useValue: {}}, {provide: LoginService, useValue: serviceSpy}, {provide: Router, useValue: {}}, {provide: EventAggregatorService, useValue: {}}],
      declarations: [HeaderComponent]
  })
      .compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(HeaderComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
