import {ComponentFixture, TestBed} from '@angular/core/testing';

import {IRouteDTO} from "../../../../model/routes/IRouteDTO";
import {RouteService} from "../../../../services/route.service";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule, NgForm} from "@angular/forms";
import {of, throwError} from "rxjs";
import {IWarehouseDto} from "../../../../dtos/warehouse/IWarehouseDto";
import {WarehouseService} from "../../../../services/warehouse.service";
import {RouteEditComponent} from "./route-edit.component";
import {ActivatedRoute} from "@angular/router";

const expectedRoute: IRouteDTO =
    { routeId: "PTX1", idDepartureWarehouse: "PT1", idArrivalWarehouse: "PT2", distance: 120, time: 60, energy: 240, extraTime: 5, }

const expectedWarehouses: IWarehouseDto[] =
    [{ identifier: "PT1", designation: 'Póvoa de Varzim', streetName: "Rua do ABC", doorNumber: 12, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 40.214556, longitude: 12.456923, active: true},
        { identifier: "PT2", designation: 'Vila do Conde', streetName: "Rua de Diogo Cão", doorNumber: 15, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 41.214556, longitude: 11.456923, active: true}];

describe('RouteEditComponent', () => {
    let component: RouteEditComponent;
    let fixture: ComponentFixture<RouteEditComponent>;
    let serviceSpy: any

    beforeEach(async () => {
        serviceSpy = jasmine.createSpyObj('RouteService', ['getRoute', 'createRoute', 'updateRoute']);
        const warehouseServiceSpy = jasmine.createSpyObj('WarehouseService', ['getWarehouses', 'getActiveWarehouses']);
        const params = {
            snapshot: {
                paramMap: {
                    get: () => expectedRoute.routeId
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
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: RouteService, useValue: serviceSpy}, {provide: WarehouseService, useValue: warehouseServiceSpy}, {provide: ActivatedRoute, useValue: params}],
            declarations: [RouteEditComponent]
        })
            .compileComponents();

        serviceSpy.getRoute.and.returnValue(of(expectedRoute));
        serviceSpy.createRoute.and.returnValue(of({}));
        serviceSpy.updateRoute.and.returnValue(of({}));
        warehouseServiceSpy.getWarehouses.and.returnValue(of(expectedWarehouses));
        warehouseServiceSpy.getActiveWarehouses.and.returnValue(of(expectedWarehouses));

        fixture = TestBed.createComponent(RouteEditComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getRoute should return expected route', () => {
        expect(component).toBeTruthy();

        expect(component.routeModel).toBeDefined();
        expect(component.routeModel?.routeId).toEqual(expectedRoute.routeId);
    });

    it('getWarehouses should return expected warehouses', () => {
        expect(component).toBeTruthy();

        expect(component.warehouses).toBeDefined();
        expect(component.warehouses.length).toBeGreaterThan(0);
        expect(component.warehouses).toEqual(expectedWarehouses);
    });

    it('createRoute fails when it already exists', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedRoute,
            resetForm(value?: any) {
            }
        };

        serviceSpy.createRoute.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Already exists"}}})));

        component.create(testForm)
        expect(component.errorMessage).toEqual("Already exists");
    });

    it('createRoute succeeds when it does not exist', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedRoute,
            resetForm(value?: any) {
            }
        };

        component.create(testForm)
        expect(component.success).toEqual(true);
    });

    it('editRoute succeeds with valid values', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedRoute,
            resetForm(value?: any) {
            }
        };

        component.edit(testForm)
        expect(component.success).toEqual(true);
    });
});
