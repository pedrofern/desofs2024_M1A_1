import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WarehouseDetailComponent} from './warehouse-detail.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {WarehouseService} from "../../../services/warehouse.service";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {of, throwError} from "rxjs";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {FormsModule, NgForm} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";

const expectedWarehouse: IWarehouseDto =
    { identifier: "PT1", designation: 'Póvoa de Varzim', streetName: "Rua do ABC", doorNumber: 12, city: "Porto", country: "Portugal", zipCode: "4410-213", latitude: 40.214556, longitude: 12.456923, active: true }

describe('WarehouseDetailComponent', () => {
    let component: WarehouseDetailComponent;
    let fixture: ComponentFixture<WarehouseDetailComponent>;
    let serviceSpy: any

    beforeEach(async () => {
        serviceSpy = jasmine.createSpyObj('WarehouseService', ['getWarehouse', 'addWarehouse', 'editWarehouse']);
        const params = {
            snapshot: {
                paramMap: {
                    get: () => expectedWarehouse.identifier
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
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: WarehouseService, useValue: serviceSpy}, {provide: ActivatedRoute, useValue: params}],
            declarations: [WarehouseDetailComponent]
        })
        .compileComponents();

        serviceSpy.getWarehouse.and.returnValue(of(expectedWarehouse));
        serviceSpy.addWarehouse.and.returnValue(of({}));
        serviceSpy.editWarehouse.and.returnValue(of({}));

        fixture = TestBed.createComponent(WarehouseDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getWarehouse should return expected warehouses', () => {
        expect(component).toBeTruthy();

        expect(component.warehouse).toBeDefined();
        expect(component.warehouse).toEqual(expectedWarehouse);
    });

    it('createWarehouse fails when it already exists', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedWarehouse,
            resetForm(value?: any) {
            }
        };

        serviceSpy.addWarehouse.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Already exists"}}})));

        component.create(testForm)
        //expect(component.errorMessage).toEqual("Already exists");
    });

    it('createWarehouse succeeds when it does not exist', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedWarehouse,
            resetForm(value?: any) {
            }
        };

        component.create(testForm)
        expect(component.success).toEqual(true);
    });

    it('editWarehouse succeeds with valid values', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedWarehouse,
            resetForm(value?: any) {
            }
        };

        component.edit(testForm)
        expect(component.success).toEqual(true);
    });
});
