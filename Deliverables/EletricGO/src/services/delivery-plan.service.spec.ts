import {TestBed} from '@angular/core/testing';

import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {TruckService} from "./truck.service";
import {MessageService} from "./message.service";
import {HttpClient} from "@angular/common/http";
import {DeliveryPlanService} from "./delivery-plan.service";

describe('DeliveryPlanService', () => {
  let service: DeliveryPlanService;
    let httpClient: HttpClient;
    let httpTestingController: HttpTestingController;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [TruckService, MessageService],
            imports: [ HttpClientTestingModule ]
        }).compileComponents();

        // Inject the http service and test controller for each test
        service = TestBed.inject(DeliveryPlanService);
        httpClient = TestBed.inject(HttpClient);
        httpTestingController = TestBed.inject(HttpTestingController);
    });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
