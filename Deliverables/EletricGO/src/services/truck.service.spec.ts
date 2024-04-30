import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
import IUpdateTruckDTO from 'src/dtos/truck/IUpdateTruckDTO';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';

import { TruckService } from './truck.service';

describe('TruckService', () => {
  let service: TruckService;
  let httpClient: HttpClient  ;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [TruckService, MessageService],
      imports: [ HttpClientTestingModule ]
    }).compileComponents();

    // Inject the http service and test controller for each test
    service = TestBed.inject(TruckService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  // TESTS //

  //Test addTruck on Service
  it('should return expected added truck (HttpClient called once)', () => {
    const createdTruck: ITruckDTO = { truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true };
    const expectedTruck: ITruckDTO = { truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true };
  
    // Make an HTTP POST request
    service.addTruck(createdTruck).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedTruck)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APITrucks);

    // Assert that the request is a POST.
    expect(req.request.method).toEqual('POST');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedTruck);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

    //Test updateTruck on Service
  it('should return expected updated truck (HttpClient called once)', () => {
    const editedTruck: IUpdateTruckDTO = { tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true };
    const expectedTruck: IUpdateTruckDTO = { tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true };
    const truckId = 'AA-00-AA';
  
    // Make an HTTP PUT request
    service.updateTruck(truckId, editedTruck).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedTruck)
    );
  
    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APITrucks + truckId);
  
    // Assert that the request is a PUT.
    expect(req.request.method).toEqual('PUT');
  
    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedTruck);
  
    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test getTruck on Service
  it('should return expected truck (HttpClient called once)', () => {
    const truckId = 'AA-00-AA';
    const expectedTruck: ITruckDTO = { truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true };
    // Make an HTTP GET request
    service.getTruck(truckId).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedTruck)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(`${environment.APITrucks}/${truckId}`);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedTruck);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  //Test getActiveTrucks on Service
  it('should return expected trucks (HttpClient called once)', () => {
    const expectedTrucks: ITruckDTO[] =
      [{ truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true }, 
      { truckId: 'BB-00-BB', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true }];

    // Make an HTTP GET request
    service.getActiveTrucks().subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedTrucks)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APITrucks + 'active');

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedTrucks);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });
});
