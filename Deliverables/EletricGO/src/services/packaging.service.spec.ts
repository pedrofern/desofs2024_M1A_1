import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { IPackagingDTO } from 'src/dtos/packaging/IPackagingDTO';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';

import { PackagingService } from './packaging.service';

describe('PackagingService', () => {
  let service: PackagingService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [PackagingService, MessageService],
      imports: [HttpClientTestingModule]
    }).compileComponents();

    // Inject the http service and test controller for each test
    service = TestBed.inject(PackagingService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  //TESTS
  it('should return expected added packaging (HttpClient called once)', () => {
    const originalPackagings: IPackagingDTO = { packagingId: "P01", deliveryId: 1, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:30", unloadTime: "01:00" }
    const expectedPackagings: IPackagingDTO = { packagingId: "P01", deliveryId: 1, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:00", unloadTime: "01:30" }

    // Make an HTTP POST request
    service.createPackaging(originalPackagings).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedPackagings)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIPackagings);

    // Assert that the request is a POST.
    expect(req.request.method).toEqual('POST');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedPackagings);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  it('should return expected updated packaging (HttpClient called once)', () => {
      const originalPackaging: IPackagingDTO = { packagingId: "P01", deliveryId: 1, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:30", unloadTime: "01:00" }
      const expectedPackaging: IPackagingDTO = { packagingId: "P01", deliveryId: 1, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:00", unloadTime: "01:30" }
    const packagingId = "P01"

    // Make an HTTP PUT request
    service.editPackaging(packagingId, originalPackaging).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedPackaging)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIPackagings + packagingId);

    // Assert that the request is a PUT.
    expect(req.request.method).toEqual('PUT');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedPackaging);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  it('should return expected packaging (HttpClient called once)', () => {
    const expectedPackaging: IPackagingDTO = { packagingId: "P01", deliveryId: 1, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:00", unloadTime: "01:30" }
    const packagingId = "P01"

    // Make an HTTP GET request
    service.getPackaging(packagingId).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedPackaging)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(`${environment.APIPackagings}/${packagingId}`);

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedPackaging);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  it('should return expected packagings (HttpClient called once)', () => {
    const expectedPackagings: IPackagingDTO[] = [
        { packagingId: "P01", deliveryId: 1, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:00", unloadTime: "01:30" },
        { packagingId: "P02", deliveryId: 2, truckId: 123, x: 1, y: 2, z: 3, loadTime: "01:00", unloadTime: "01:30" }];

    // Make an HTTP GET request
    service.getPackagings('1', '1', 'asc',2,0).subscribe(data =>
      // When observable resolves, result should match test data
      expect(data).toEqual(expectedPackagings)
    );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne(environment.APIPackagings + 'filter?0=1&sortBy=1&sortOrder=asc&pageIndex=2&pageSize=0');

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(expectedPackagings);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });
});
