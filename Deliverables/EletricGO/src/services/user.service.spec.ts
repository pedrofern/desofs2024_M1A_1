import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { ICreateUserDTO } from 'src/dtos/user/ICreateUserDTO';
import { IUpdateUserDTO } from 'src/dtos/user/IUpdateUserDTO';
import { IUserDTO } from 'src/dtos/user/IUserDTO';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';

import { UserService } from './user.service';

let userService: UserService;
let httpClient: HttpClient;
let httpTestingController: HttpTestingController;

describe('UserService', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [UserService, MessageService],
      imports: [ HttpClientTestingModule ]
    }).compileComponents();

    // Inject the http service and test controller for each test
    userService = TestBed.inject(UserService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  /// Tests begin ///
  //Test AddUser on Service
  it('should return expected addUser (HttpClient called once)', () => {
    const createUser: ICreateUserDTO = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
    password: '123', role: '1'};
    const expectedUser: ICreateUserDTO = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
    password: '123', role: '1'};

    // Make an HTTP POST request
    userService.addUser(createUser).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedUser)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APILogin + 'signup');

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('POST');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedUser);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test EditUser on Service
  it('should return expected editUser (HttpClient called once)', () => {
    const editUser: IUpdateUserDTO = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', role: '1'};
    const expectedUser: IUpdateUserDTO = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', role: '1'};
    const userEmail = 'teste@isep.ipp.pt';

    // Make an HTTP PUT request
    userService.editUser(userEmail, editUser).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedUser)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers + userEmail);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('PUT');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedUser);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetUser on Service
  it('should return expected getUser (HttpClient called once)', () => {
    const userEmail = 'teste@isep.ipp.pt';
    const expectedUser: IUserDTO = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
    password: '123', role: '1', roleName: 'Administrador', active: true};

    // Make an HTTP GET request
    userService.getUser(userEmail).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedUser)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers + userEmail);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedUser);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetUsers on Service
  it('should return expected getUsers (HttpClient called once)', () => {
    const expectedUsers: IUserDTO[] =
      [{ userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
      password: '123', role: '1', roleName: 'Administrador', active: true},
      { userName: 'Teste 2', firstName: 'Teste2', lastName: 'Teste2', phoneNumber: '123', email: 'teste2@isep.ipp.pt', 
    password: '123', role: '1', roleName: 'Administrador', active: true}];

    // Make an HTTP GET request
    userService.getUsers().subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedUsers)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedUsers);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetUserByEmail on Service
  it('should return expected getUserByEmail (HttpClient called once)', () => {
    const userEmail = 'teste@isep.ipp.pt';
    const expectedUsers: IUserDTO[] =
      [{ userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
      password: '123', role: '1', roleName: 'Administrador', active: true},
      { userName: 'Teste 2', firstName: 'Teste2', lastName: 'Teste2', phoneNumber: '123', email: 'teste2@isep.ipp.pt', 
    password: '123', role: '1', roleName: 'Administrador', active: true}];

    // Make an HTTP GET request
    userService.getUserByEmail(userEmail).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedUsers)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers + userEmail);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedUsers);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetRoles on Service
  it('should return expected getRoles (HttpClient called once)', () => {
    const expectedRoles: IRoleDTO[] =
      [{ id: '1', name: 'Teste1'},
      { id: '2', name: 'Teste2'}];

    // Make an HTTP GET request
    userService.getRoles().subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedRoles)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIRoles);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedRoles);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });
});
