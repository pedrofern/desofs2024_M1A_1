import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WarehousesComponent} from "./warehouses.component";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {of} from "rxjs";
import {WarehouseService} from "../../../services/warehouse.service";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const expectedWarehouses: IWarehouseDto[] =
    [{ identifier: "PT1", designation: 'Póvoa de Varzim', streetName: "Rua do ABC", doorNumber: 12, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 40.214556, longitude: 12.456923, active: true},
        { identifier: "PT2", designation: 'Vila do Conde', streetName: "Rua de Diogo Cão", doorNumber: 15, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 41.214556, longitude: 11.456923, active: true  }];

describe('WarehousesComponent', () => {
    let component: WarehousesComponent;
    let fixture: ComponentFixture<WarehousesComponent>;

    beforeEach(async () => {
        const serviceSpy = jasmine.createSpyObj('WarehouseService', ['getWarehouses', 'getActiveWarehouses', 'getFilterWarehouses']);

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
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: WarehouseService, useValue: serviceSpy}],
            declarations: [WarehousesComponent]
        })
        .compileComponents();

        serviceSpy.getFilterWarehouses.and.returnValue(of(expectedWarehouses));
        serviceSpy.getWarehouses.and.returnValue(of(expectedWarehouses));
        serviceSpy.getActiveWarehouses.and.returnValue(of(expectedWarehouses));

        fixture = TestBed.createComponent(WarehousesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getWarehouses should return expected warehouses', () => {
        expect(component).toBeTruthy();

        expect(component.warehouses).toBeDefined();
        expect(component.warehouses.length).toBeGreaterThan(0);
        expect(component.warehouses[0]).toEqual(expectedWarehouses[0]);
    });
});
