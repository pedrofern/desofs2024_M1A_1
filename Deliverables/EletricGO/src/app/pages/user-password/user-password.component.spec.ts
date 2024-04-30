import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { IRoleDTO } from 'src/dtos/role/IRoleDTO';
import { UserService } from 'src/services/user.service';

import { UserPasswordComponent } from './user-password.component';

const expectedRoles: IRoleDTO[] =
      [{ id: '1', name: 'Teste1'},
      { id: '2', name: 'Teste2'}];

describe('UserPasswordComponent', () => {
  let component: UserPasswordComponent;
  let fixture: ComponentFixture<UserPasswordComponent>;
  let serviceSpy: any;

  beforeEach(async () => {
    serviceSpy = jasmine.createSpyObj('UserService', ['getUsers', 'getUser', 'getRoles', 'addUser', 'editUser', 'editPassword']);

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
    }, {provide: ActivatedRoute, useValue: {}}],
    declarations: [UserPasswordComponent]
  })
    .compileComponents();

      serviceSpy.getRoles.and.returnValue(of(expectedRoles));

    fixture = TestBed.createComponent(UserPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
