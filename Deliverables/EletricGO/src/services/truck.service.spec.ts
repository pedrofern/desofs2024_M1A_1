import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
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
      const battery = { batteryId: 1, maximumBattery: 100, autonomy: 100, chargingTime: 100 };
    const createdTruck: ITruckDTO = { truckId: 1, tare: 100, loadCapacity: 100, active: true, battery: battery };
    const expectedTruck: ITruckDTO = { truckId: 2, tare: 100, loadCapacity: 100, active: true, battery: battery };

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
      const battery = { batteryId: 1, maximumBattery: 100, autonomy: 100, chargingTime: 100 };
      const editedTruck: ITruckDTO = { truckId: 1, tare: 100, loadCapacity: 100, active: true, battery: battery };
      const expectedTruck: ITruckDTO = { truckId: 1, tare: 100, loadCapacity: 100, active: true, battery: battery };
    const truckId = 1;

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
      const battery = { batteryId: 1, maximumBattery: 100, autonomy: 100, chargingTime: 100 };
      const expectedTruck: ITruckDTO = { truckId: 1, tare: 100, loadCapacity: 100, active: true, battery: battery };
      const truckId = 1;
    // Make an HTTP GET request
    service.getTruck(truckId).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedTruck)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(`${environment.APITrucks}${truckId}`);

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
      const battery = { batteryId: 1, maximumBattery: 100, autonomy: 100, chargingTime: 100 };
      const battery2 = { batteryId: 2, maximumBattery: 100, autonomy: 100, chargingTime: 100 };
    const expectedTrucks: ITruckDTO[] =
      [{ truckId: 1, tare: 100, loadCapacity: 100, active: true, battery: battery },
          { truckId: 2, tare: 100, loadCapacity: 100, active: true, battery: battery2 }];

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
