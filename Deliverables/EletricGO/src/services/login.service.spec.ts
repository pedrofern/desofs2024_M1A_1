import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { ILogin } from 'src/dtos/login/ILogin';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { environment } from 'src/environments/environment';
import { IUser } from 'src/model/IUser';
import { LoginService } from './login.service';
import { MessageService } from './message.service';

let loginService: LoginService;
let httpClient: HttpClient;
let httpTestingController: HttpTestingController;

const expectedLogin: IUser = { userName: 'Teste 1', firstName: 'Teste', lastName: 'Teste', phoneNumber: '123', email: 'teste@isep.ipp.pt', 
password: '123', role: '1', roleName: 'Administrador', active: true};
const expectedRole: IRoleDTO = {id: '1', name: 'Administrador'}

describe('LoginService', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [LoginService, MessageService],
      imports: [ HttpClientTestingModule ]
    }).compileComponents();

    // Inject the http service and test controller for each test
    loginService = TestBed.inject(LoginService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  // Tests begin ///
  //Test validateLogin on Service
  it('should return expected validateLogin (HttpClient called once)', () => {

    const createLogin: ILogin = {email: 'teste@isep.ipp.pt', password: '123'}

    // Make an HTTP POST request
    loginService.validateLogin(createLogin).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedLogin)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers+'signin');

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('POST');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedLogin);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test validateLoginGoogle on Service
  it('should return expected validateLoginGoogle (HttpClient called once)', () => {
    const token = 'qweerty123'

    // Make an HTTP POST request
    loginService.validateLoginGoogle(token).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedLogin)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers+'loginGoogle/'+token);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedLogin);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test validateLoginGoogle on Service
  it('should return expected getRoleByUser (HttpClient called once)', () => {
    const roleId = '1'

    // Make an HTTP POST request
    loginService.getRoleByUser(roleId).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedRole)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIUsers+ 'roleByUser/' + roleId);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedRole);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });
});
