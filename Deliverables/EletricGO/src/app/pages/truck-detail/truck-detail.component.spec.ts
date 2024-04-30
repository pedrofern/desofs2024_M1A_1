import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {FormsModule, NgForm} from '@angular/forms';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {RouterTestingModule} from '@angular/router/testing';
import {of, throwError} from 'rxjs';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
import {TruckService} from 'src/services/truck.service';

import {TruckDetailComponent} from './truck-detail.component';
import {ActivatedRoute} from "@angular/router";

const expectedTruck: ITruckDTO =
    {truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true};

describe('TruckDetailComponent', () => {
    let component: TruckDetailComponent;
    let fixture: ComponentFixture<TruckDetailComponent>;
    let serviceSpy: any

    beforeEach(async () => {
        serviceSpy = jasmine.createSpyObj('TruckService', ['getTruck', 'addTruck', 'updateTruck']);
        const params = {
            snapshot: {
                paramMap: {
                    get: () => expectedTruck.truckId
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
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: TruckService, useValue: serviceSpy}, {provide: ActivatedRoute, useValue: params}],
            declarations: [TruckDetailComponent]
        })
            .compileComponents();

        serviceSpy.getTruck.and.returnValue(of(expectedTruck));
        serviceSpy.addTruck.and.returnValue(of({}));
        serviceSpy.updateTruck.and.returnValue(of({}));

        fixture = TestBed.createComponent(TruckDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getTruck', () => {
        expect(component).toBeTruthy();

        expect(component.truck).toBeDefined();
        expect(component.truck).toEqual(expectedTruck);
    });

    it('createTruck fails when it already exists', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedTruck,
            resetForm(value?: any) {
            }
        };

        serviceSpy.addTruck.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Already exists"}}})));

        component.create(testForm)
        expect(component.errorMessage).toEqual("Already exists");
    });

    it('createTruck succeeds when it does not exist', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedTruck,
            resetForm(value?: any) {
            }
        };

        component.create(testForm)
        expect(component.success).toEqual(true);
    });

    it('editTruck succeeds with valid values', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedTruck,
            resetForm(value?: any) {
            }
        };

        component.edit(testForm)
        expect(component.success).toEqual(true);
    });
});
