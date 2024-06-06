import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CookieService } from 'ngx-cookie-service';
import { of } from 'rxjs';
import { IUserDTO } from 'src/dtos/user/IUserDTO';
import { IUser } from 'src/model/IUser';
import { UserService } from 'src/services/user.service';
import { UsersComponent } from './users.component';

const expectedUser: IUser[] = [{ userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
password: '123', role: 'ADMIN', active: true}];

describe('UsersComponent', () => {
  let component: UsersComponent;
  let fixture: ComponentFixture<UsersComponent>;

  beforeEach(async () => {
    const serviceSpy = jasmine.createSpyObj('UserService', ['getUsers']);
    const cookieServiceSpy = jasmine.createSpyObj('CookieService', ['get']);

    await TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule,
            MatDialogModule,
            MatSortModule,
            MatPaginatorModule,
            BrowserAnimationsModule
        ],
        providers: [{provide: MatDialogRef, useValue: {}}, {provide: UserService, useValue: serviceSpy}, {provide: CookieService, useValue: cookieServiceSpy}],
        declarations: [UsersComponent]
    })
        .compileComponents();
        
    cookieServiceSpy.get.and.returnValue(of('ADMIN'));
    serviceSpy.getUsers.and.returnValue(of(expectedUser));

    fixture = TestBed.createComponent(UsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
});

  it('should create', () => {
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
