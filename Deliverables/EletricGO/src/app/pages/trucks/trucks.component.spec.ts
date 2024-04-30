import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {of} from 'rxjs';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
import {TruckService} from 'src/services/truck.service';

import {TrucksComponent} from './trucks.component';

const expectedTrucks: ITruckDTO[] =
    [{truckId: 'AA-00-AA', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true},
        {truckId: 'BB-00-BB', tare: 100, loadCapacity: 100, maximumBattery: 100, autonomy: 100, chargingTime: 100, active: true}];

describe('TrucksComponent', () => {
    let component: TrucksComponent;
    let fixture: ComponentFixture<TrucksComponent>;

    beforeEach(async () => {

        const serviceSpy = jasmine.createSpyObj('TruckService', ['getTrucks', 'getTotalRecords']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MatDialogModule,
                MatSortModule,
                MatPaginatorModule,
                BrowserAnimationsModule
            ],
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: TruckService, useValue: serviceSpy}],
            declarations: [TrucksComponent]
        })
            .compileComponents();

        serviceSpy.getTotalRecords.and.returnValue(of(2));
        serviceSpy.getTrucks.and.returnValue(of(expectedTrucks));

        fixture = TestBed.createComponent(TrucksComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getTrucks should return expected trucks', () => {
        expect(component).toBeTruthy();

        expect(component.trucks).toBeDefined();
        expect(component.trucks.length).toBeGreaterThan(0);
        expect(component.trucks[0]).toEqual(expectedTrucks[0]);
    });
});
