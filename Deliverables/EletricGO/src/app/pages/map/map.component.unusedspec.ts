import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapComponent } from './map.component';
import {WarehousesComponent} from "../warehouses/warehouses.component";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {WarehouseService} from "../../../services/warehouse.service";
import {of} from "rxjs";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";

const expectedWarehouses: IWarehouseDto[] =
    [{ identifier: "PT1", designation: 'Póvoa de Varzim', streetName: "Rua do ABC", doorNumber: 12, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 40.214556, longitude: 12.456923, height: 200},
        { identifier: "PT2", designation: 'Vila do Conde', streetName: "Rua de Diogo Cão", doorNumber: 15, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 41.214556, longitude: 11.456923, height: 300  }];

describe('MapComponent', () => {
    let component: MapComponent;
    let fixture: ComponentFixture<MapComponent>;

    beforeEach(async () => {
        const serviceSpy = jasmine.createSpyObj('WarehouseService', ['getWarehouses']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            providers: [{provide: WarehouseService, useValue: serviceSpy}],
            declarations: [WarehousesComponent]
        })
            .compileComponents();

        serviceSpy.getWarehouses.and.returnValue(of(expectedWarehouses));

        fixture = TestBed.createComponent(MapComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
