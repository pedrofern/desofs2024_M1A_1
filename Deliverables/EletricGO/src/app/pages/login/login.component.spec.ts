import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { of } from 'rxjs';
import { IUser } from 'src/model/IUser';
import { EventAggregatorService } from 'src/services/event-aggregator.service';
import { LoginService } from 'src/services/login.service';

import { LoginComponent } from './login.component';

const expectedLogin: IUser = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
password: '123', role: '1', roleName: 'Administrador', active: true};

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    const serviceSpy = jasmine.createSpyObj('LoginService', ['validateLogin', 'validateLoginGoogle', 'validateIsLoggedIn', 'logout', 'getRoleByUser']);
    const eventServiceSpy = jasmine.createSpyObj('EventAggregatorService', ['LogoutRefresh']);

    await TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule,
            MatDialogModule,
            MatSortModule,
            MatPaginatorModule,
            BrowserAnimationsModule,
            FormsModule
        ],
        providers: [{provide: MatDialogRef, useValue: {}}, {provide: LoginService, useValue: serviceSpy}, {provide: Router, useValue: {}}, {provide: EventAggregatorService, useValue: {}}, {provide: CookieService, useValue: {}}],
        declarations: [LoginComponent]
    })
        .compileComponents();

    serviceSpy.validateLogin.and.returnValue(of(expectedLogin));
    serviceSpy.validateLoginGoogle.and.returnValue(of(expectedLogin));
});

  it('should create', () => {
    const fixture = TestBed.createComponent(LoginComponent);
        const app = fixture.componentInstance;
        expect(app).toBeTruthy();
  });
});
