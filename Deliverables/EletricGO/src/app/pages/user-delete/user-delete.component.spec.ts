import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { IUser } from 'src/model/IUser';
import { UserService } from 'src/services/user.service';

import { UserDeleteComponent } from './user-delete.component';

const expectedUser: IUser = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
password: '123', role: 'ADMIN', active: true};

const expectedRoles: IRoleDTO[] =
      [{ id: '1', name: 'Teste1'},
      { id: '2', name: 'Teste2'}];

describe('UserDeleteComponent', () => {
  let component: UserDeleteComponent;
  let fixture: ComponentFixture<UserDeleteComponent>;
  let serviceSpy: any;

  beforeEach(async () => {
    serviceSpy = jasmine.createSpyObj('UserService', ['getUsers', 'getUser', 'getRoles', 'addUser', 'editUser', 'deleteUser']);
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
    declarations: [UserDeleteComponent]
  })
    .compileComponents();

      serviceSpy.getUser.and.returnValue(of(expectedUser));
      serviceSpy.getRoles.and.returnValue(of(expectedRoles));

    fixture = TestBed.createComponent(UserDeleteComponent);
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

  it('deleteUser fails when it already exists', () => {
    expect(component).toBeTruthy();

    const testForm = <NgForm>{
        value: expectedUser,
        resetForm(value?: any) {
        }
    };

    serviceSpy.deleteUser.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Not Found"}}})));

    component.delete(testForm)
    expect(component.errorMessage).toEqual("Not Found");
  });
});
