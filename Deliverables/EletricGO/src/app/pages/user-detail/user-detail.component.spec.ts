import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { IRoleDTO } from 'src/dtos/role/IRoleDTO';
import { IUser } from 'src/model/IUser';
import { UserService } from 'src/services/user.service';

import { UserDetailComponent } from './user-detail.component';

const expectedUser: IUser = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
password: '123', role: 'ADMIN', active: true};

const expectedRoles: IRoleDTO[] =
      [{ id: '1', name: 'Teste1'},
      { id: '2', name: 'Teste2'}];

describe('UserDetailComponent', () => {
  let component: UserDetailComponent;
  let fixture: ComponentFixture<UserDetailComponent>;
  let serviceSpy: any;

  beforeEach(async () => {
    serviceSpy = jasmine.createSpyObj('UserService', ['getUsers', 'getUser', 'getRoles', 'addUser', 'editUser']);
    const params = {
      snapshot: {
          paramMap: {
              get: () => expectedUser.email
          },
      },
  }

  await TestBed.configureTestingModule({
    imports: [
        HttpClientTestingModule,
        MatDialogModule,
        RouterTestingModule,
        FormsModule
    ],
    providers: [{provide: MatDialogRef, useValue: {}}, {
        provide: UserService,
        useValue: serviceSpy
    }, {provide: ActivatedRoute, useValue: params}],
    declarations: [UserDetailComponent]
  })
    .compileComponents();

      serviceSpy.getUser.and.returnValue(of(expectedUser));
      serviceSpy.getRoles.and.returnValue(of(expectedRoles));

    fixture = TestBed.createComponent(UserDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('getUser should return expected delivery', () => {
    expect(component).toBeTruthy();

    expect(component.user).toBeDefined();
    expect(component.user).toEqual(expectedUser);
  });

  it('getRoles should return expected warehouses', () => {
      expect(component).toBeTruthy();

      expect(component.user).toBeDefined();
      expect(component.user).toEqual(expectedUser);
      expect(component.roles.length).toBeGreaterThan(0);
      expect(component.roles[0]).toEqual(expectedRoles[0]);
  });

  it('createUser fails when it already exists', () => {
    expect(component).toBeTruthy();

    const testForm = <NgForm>{
        value: expectedUser,
        resetForm(value?: any) {
        }
    };

    serviceSpy.addUser.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Already exists"}}})));

    component.create(testForm)
    expect(component.errorMessage).toEqual("Already exists");
  });
});
