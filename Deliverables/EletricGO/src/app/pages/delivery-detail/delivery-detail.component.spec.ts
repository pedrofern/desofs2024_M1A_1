import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {of, throwError} from 'rxjs';
import {DeliveryService} from 'src/services/delivery.service';
import {RouterTestingModule} from "@angular/router/testing";
import {DeliveryDetailComponent} from './delivery-detail.component';
import {FormsModule, NgForm} from '@angular/forms';
import {ActivatedRoute} from "@angular/router";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {IDeliveryDto} from "../../../dtos/delivery/IDeliveryDto";

const expectedDelivery: IDeliveryDto =
    {deliveryId: 1, deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99'};

const expectedWarehouse: IWarehouseDto[] =
    [{
        identifier: '1',
        designation: 'Teste 1',
        streetName: 'Rua de Cima',
        doorNumber: 99,
        city: 'Teste 1',
        country: 'Teste 1',
        zipCode: 'Teste 1',
        latitude: 40,
        longitude: 9,
        height: 100,
        active: true
    },
        {
            identifier: '2',
            designation: 'Teste 2',
            streetName: 'Rua de Cima',
            doorNumber: 99,
            city: 'Teste 2',
            country: 'Teste 2',
            zipCode: 'Teste 2',
            latitude: 41,
            longitude: 8,
            height: 200,
            active: true
        }];


describe('DeliveryDetailComponent', () => {
    let component: DeliveryDetailComponent;
    let fixture: ComponentFixture<DeliveryDetailComponent>;
    let serviceSpy: any

    beforeEach(async () => {
        serviceSpy = jasmine.createSpyObj('DeliveryService', ['getDelivery', 'getWarehouses', 'addDelivery', 'editDelivery']);
        const params = {
            snapshot: {
                paramMap: {
                    get: () => expectedDelivery.deliveryId
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
                provide: DeliveryService,
                useValue: serviceSpy
            }, {provide: ActivatedRoute, useValue: params}],
            declarations: [DeliveryDetailComponent]
        })
            .compileComponents();

        serviceSpy.getDelivery.and.returnValue(of(expectedDelivery));
        serviceSpy.getWarehouses.and.returnValue(of(expectedWarehouse));
        serviceSpy.addDelivery.and.returnValue(of({}));
        serviceSpy.editDelivery.and.returnValue(of({}));

        fixture = TestBed.createComponent(DeliveryDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getDelivery should return expected delivery', () => {
        expect(component).toBeTruthy();

        expect(component.delivery).toBeDefined();
        expect(component.delivery).toEqual(expectedDelivery);
    });

    it('getWarehouses should return expected warehouses', () => {
        expect(component).toBeTruthy();

        expect(component.delivery).toBeDefined();
        expect(component.delivery).toEqual(expectedDelivery);
        expect(component.warehouses.length).toBeGreaterThan(0);
        expect(component.warehouses[0]).toEqual(expectedWarehouse[0]);
    });

    it('createDelivery fails when it already exists', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedDelivery,
            resetForm(value?: any) {
            }
        };

        serviceSpy.addDelivery.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Already exists"}}})));

        component.create(testForm)
        expect(component.errorMessage).toEqual("Already exists");
    });

    it('createDelivery succeeds when it does not exist', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedDelivery,
            resetForm(value?: any) {
            }
        };

        component.create(testForm)
        expect(component.success).toEqual(true);
    });

    it('editDelivery succeeds with valid values', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedDelivery,
            resetForm(value?: any) {
            }
        };

        component.edit(testForm)
        expect(component.success).toEqual(true);
    });
});
