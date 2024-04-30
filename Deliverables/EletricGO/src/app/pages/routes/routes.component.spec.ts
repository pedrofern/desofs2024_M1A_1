import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RoutesComponent} from './routes.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {WarehouseService} from "../../../services/warehouse.service";
import {WarehousesComponent} from "../warehouses/warehouses.component";
import {of} from "rxjs";
import {IRoute} from "../../../model/routes/IRoute";
import {RouteService} from "../../../services/route.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {WarehouseDetailComponent} from "../warehouse-detail/warehouse-detail.component";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GeneralItemDTO } from 'src/dtos/shared/generalItemDto';

const expectedRoutes: IRoute[] =
    [{ routeId: "PTX1", idDepartureWarehouse: "PT1", idArrivalWarehouse: "PT2", distance: 120, time: 60, energy: 240, extraTime: 5, },
        { routeId: "PTX2", idDepartureWarehouse: "PT2", idArrivalWarehouse: "PT3", distance: 120, time: 60, energy: 240, extraTime: 5}];

const expectedGeneralRoutes: GeneralItemDTO[] = [
            {id: "R01", description: "First route"},
            {id: "R02", description: "Second route"},
            {id: "R03", description: "Third route"},
            {id: "R04", description: "Fourth route"}
        ];

const expectedWarehouses: IWarehouseDto[] =
    [{ identifier: "PT1", designation: 'Póvoa de Varzim', streetName: "Rua do ABC", doorNumber: 12, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 40.214556, longitude: 12.456923, height: 200, active: true},
        { identifier: "PT2", designation: 'Vila do Conde', streetName: "Rua de Diogo Cão", doorNumber: 15, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 41.214556, longitude: 11.456923, height: 300, active: true  }];

describe('RoutesComponent', () => {
    let component: RoutesComponent;
    let fixture: ComponentFixture<RoutesComponent>;

    beforeEach(async () => {
        const serviceSpy = jasmine.createSpyObj('RouteService', ['getRoute', 'getRoutes', 'getTotalRecords', 'createRoute', 'updateRoute']);
        const warehouseSpy = jasmine.createSpyObj('WarehouseService', ['getWarehouses', 'getActiveWarehouses']);

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
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: RouteService, useValue: serviceSpy}, {provide: WarehouseService, useValue: warehouseSpy}],
            declarations: [RoutesComponent]
        })
            .compileComponents();

        serviceSpy.getTotalRecords.and.returnValue(of(expectedRoutes));
        warehouseSpy.getWarehouses.and.returnValue(of(expectedWarehouses));
        warehouseSpy.getActiveWarehouses.and.returnValue(of(expectedWarehouses));

        fixture = TestBed.createComponent(RoutesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getRoutes should return expected routes', () => {
        expect(component).toBeTruthy();

        expect(component.routes).toBeDefined();
    });

    it('getWarehouses should return expected warehouses', () => {
        expect(component).toBeTruthy();

        expect(component.warehouses).toBeDefined();
        expect(component.warehouses.length).toBeGreaterThan(0);
        expect(component.warehouses).toEqual(expectedWarehouses);
    });
});
