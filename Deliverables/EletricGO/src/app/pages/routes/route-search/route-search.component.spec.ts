import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteSearchComponent } from './route-search.component';
import {IRouteDTO} from "../../../../model/routes/IRouteDTO";
import {RouteService} from "../../../../services/route.service";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {RoutesComponent} from "../routes.component";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";
import {of} from "rxjs";
import {IWarehouseDto} from "../../../../dtos/warehouse/IWarehouseDto";
import {WarehouseService} from "../../../../services/warehouse.service";
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const expectedRoutes: IRouteDTO[] =
    [{ routeId: "PTX1", idDepartureWarehouse: "PT1", idArrivalWarehouse: "PT2", distance: 120, time: 60, energy: 240, extraTime: 5, },
        { routeId: "PTX2", idDepartureWarehouse: "PT2", idArrivalWarehouse: "PT3", distance: 120, time: 60, energy: 240, extraTime: 5}]

const expectedWarehouses: IWarehouseDto[] =
    [{ identifier: "PT1", designation: 'Póvoa de Varzim', streetName: "Rua do ABC", doorNumber: 12, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 40.214556, longitude: 12.456923, active: true},
        { identifier: "PT2", designation: 'Vila do Conde', streetName: "Rua de Diogo Cão", doorNumber: 15, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 41.214556, longitude: 11.456923, active: true}];

describe('RouteSearchComponent', () => {
    let component: RouteSearchComponent;
    let fixture: ComponentFixture<RouteSearchComponent>;

    beforeEach(async () => {
        const serviceSpy = jasmine.createSpyObj('RouteService', ['getRoute', 'getRoutes', 'getTotalRecords', 'createRoute', 'updateRoute', 'getWarehouses']);
        const warehouseServiceSpy = jasmine.createSpyObj('WarehouseService', ['getWarehouses']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MatDialogModule,
                RouterTestingModule,
                FormsModule,
                MatSortModule,
                MatPaginatorModule,
                BrowserAnimationsModule
            ],
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: RouteService, useValue: serviceSpy}, {provide: WarehouseService, useValue: warehouseServiceSpy}],
            declarations: [RouteSearchComponent]
        }).compileComponents();

        serviceSpy.getTotalRecords.and.returnValue(of(expectedRoutes));
        serviceSpy.getRoutes.and.returnValue(of(expectedRoutes));
        warehouseServiceSpy.getWarehouses.and.returnValue(of(expectedWarehouses));

        fixture = TestBed.createComponent(RouteSearchComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getRoutes should return expected routes', () => {
        expect(component).toBeTruthy();

        expect(component.routes).toBeDefined();
        expect(component.routes.length).toBeGreaterThan(0);
        expect(component.routes[0].routeId).toEqual(expectedRoutes[0].routeId);
        expect(component.routes[1].routeId).toEqual(expectedRoutes[1].routeId);
    });

    it('getWarehouses should return expected warehouses', () => {
        expect(component).toBeTruthy();

        expect(component.warehouses).toBeDefined();
        // expect(component.warehouses.length).toBeGreaterThan(0);
        // expect(component.warehouses).toEqual(expectedWarehouses);
    });
});
