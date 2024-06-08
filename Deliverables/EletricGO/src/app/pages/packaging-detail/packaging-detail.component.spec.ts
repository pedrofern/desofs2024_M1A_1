import { ComponentFixture, TestBed } from '@angular/core/testing';
import {IPackagingDTO} from "../../../dtos/packaging/IPackagingDTO";
import {WarehouseDetailComponent} from "../warehouse-detail/warehouse-detail.component";
import {PackagingDetailComponent} from "./packaging-detail.component";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";
import {of} from "rxjs";
import {PackagingService} from "../../../services/packaging.service";
import {IDeliveryDto} from "../../../dtos/delivery/IDeliveryDto";
import ITruckDTO from "../../../dtos/truck/ITruckDTO";
import {TruckService} from "../../../services/truck.service";
import {DeliveryService} from "../../../services/delivery.service";

const expectedPackaging: IPackagingDTO = { packagingId: "P01", deliveryId: 1, truckId: 123,  loadTime: "01:30", unloadTime: "01:00" , x: 1, y: 2, z: 3}

const expectedDeliveries: IDeliveryDto[] =
    [{deliveryId: 1, deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99'},
        {deliveryId: 2, deliveryDate: '12-12-2022', weight: 200, warehouseId: 'M98'}];

const expectedTrucks: ITruckDTO[] =
    [{truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true},
        {truckId: 'BB-00-BB', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true}];

describe('PackagingDetailComponent', () => {
    let component: PackagingDetailComponent;
    let fixture: ComponentFixture<PackagingDetailComponent>;
    let packagingService: any

    beforeEach(async () => {
        packagingService = jasmine.createSpyObj('PackagingService', ['getPackaging', 'getActiveTrucks', 'getDeliveries']);
        const truckService = jasmine.createSpyObj('TruckService', ['getActiveTrucks']);
        const deliveryService = jasmine.createSpyObj('DeliveryService', ['getDeliveries']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MatDialogModule,
                RouterTestingModule,
                FormsModule
            ],
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: PackagingService, useValue: packagingService}, {provide: TruckService, useValue: truckService}, {provide: DeliveryService, useValue: deliveryService}],
            declarations: [WarehouseDetailComponent]
        })
            .compileComponents();

        packagingService.getPackaging.and.returnValue(of(expectedPackaging));
        truckService.getActiveTrucks.and.returnValue(of(expectedTrucks));
        deliveryService.getDeliveries.and.returnValue(of(expectedDeliveries));

        fixture = TestBed.createComponent(PackagingDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getDeliveries should return expected deliveries', () => {
        expect(component).toBeTruthy();

        expect(component.deliveries).toBeDefined();
        expect(component.deliveries).toEqual(expectedDeliveries);
    });

    it('getTrucks should return expected trucks', () => {
        expect(component).toBeTruthy();

        expect(component.trucks).toBeDefined();
        expect(component.trucks).toEqual(expectedTrucks);
        expect(component.trucks.length).toBeGreaterThan(0);
    });
});
