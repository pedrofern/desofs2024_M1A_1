import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { defer } from 'rxjs';
import { ICreateDeliveryDto } from 'src/dtos/delivery/ICreateDeliveryDto';
import { IDeliveryDto } from 'src/dtos/delivery/IDeliveryDto';
import { IUpdateDeliveryDto } from 'src/dtos/delivery/IUpdateDeliveryDto';
import { IWarehouseDto } from 'src/dtos/warehouse/IWarehouseDto';
import { environment } from 'src/environments/environment';
import { DeliveryService } from './delivery.service';
import { MessageService } from './message.service';

let deliveryService: DeliveryService;
let httpClient: HttpClient;
let httpTestingController: HttpTestingController;

describe('DeliveryService', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [DeliveryService, MessageService],
      imports: [ HttpClientTestingModule ]
    }).compileComponents();

    // Inject the http service and test controller for each test
    deliveryService = TestBed.inject(DeliveryService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  /// Tests begin ///
  //Test AddDelivery on Service
  it('should return expected addDelivery (HttpClient called once)', () => {
    const createDelivery: ICreateDeliveryDto = { deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99' };
    const expectedDelivery: ICreateDeliveryDto = { deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99' };

    // Make an HTTP POST request
    deliveryService.addDelivery(createDelivery).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedDelivery)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIDeliveries);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('POST');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedDelivery);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test EditDelivery on Service
  it('should return expected editDelivery (HttpClient called once)', () => {
    const editDelivery: IUpdateDeliveryDto = { deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99' };
    const expectedDelivery: IUpdateDeliveryDto = { deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99' };
    const deliveryId = '1';

    // Make an HTTP PUT request
    deliveryService.editDelivery(deliveryId, editDelivery).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedDelivery)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIDeliveries + deliveryId);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('PUT');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedDelivery);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetDelivery on Service
  it('should return expected getDelivery (HttpClient called once)', () => {
    const deliveryId = 1;
    const expectedDelivery: IDeliveryDto = { deliveryId: 1, deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99' };

    // Make an HTTP GET request
    deliveryService.getDelivery(deliveryId).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedDelivery)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIDeliveries + deliveryId);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedDelivery);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetDeliveries on Service
  it('should return expected getDeliveries (HttpClient called once)', () => {
    const expectedDelivery: IDeliveryDto[] =
      [{ deliveryId: 1, deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99' },
      { deliveryId: 2, deliveryDate: '12-12-2022', weight: 200, warehouseId: 'M98'  }];

    // Make an HTTP GET request
    deliveryService.getDeliveries().subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedDelivery)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIDeliveries);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedDelivery);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test GetWarehouses on Service
  it('should return expected getWarehouses (HttpClient called once)', () => {
    const expectedWarehouse: IWarehouseDto[] =
      [{ identifier: '1', designation: 'Teste 1', streetName: 'Rua de Cima', doorNumber: 99, city: 'Teste 1', country: 'Teste 1', zipCode: 'Teste 1', latitude: 40, longitude: 9, active: true },
      { identifier: '2', designation: 'Teste 2', streetName: 'Rua de Cima', doorNumber: 99, city: 'Teste 2', country: 'Teste 2', zipCode: 'Teste 2', latitude: 41, longitude: 8, active: true }];

    // Make an HTTP GET request
    deliveryService.getWarehouses().subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedWarehouse)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIWarehouses);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedWarehouse);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });
});
