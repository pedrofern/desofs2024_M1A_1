import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DeliveryPlanComponent} from './delivery-plan.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";
import {DeliveryPlanService} from "../../../services/delivery-plan.service";
import {of} from "rxjs";
import {IDeliveryPlan} from "../../../model/IDeliveryPlan";
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const expectedDeliveryPlans: IDeliveryPlan[] =
    [{ deliveryPlanId: 1, truckId: "45-XZ-12", date: '20/12/2022', deliveriesId: [1,2,3], paths: ["X", "YZ", "Z"], cost: 5},
        { deliveryPlanId: 2, truckId: "40-XZ-12", date: '24/12/2022', deliveriesId: [4,5,6], paths: ["X", "YZ", "Z"], cost: 5}];

describe('DeliveryPlanComponent', () => {
    let component: DeliveryPlanComponent;
    let fixture: ComponentFixture<DeliveryPlanComponent>;

    beforeEach(async () => {
        const serviceSpy = jasmine.createSpyObj('DeliveryPlanService', ['getDeliveryPlans', 'getTotalRecords']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MatDialogModule,
                MatSortModule,
                MatPaginatorModule,
                BrowserAnimationsModule
            ],
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: DeliveryPlanService, useValue: serviceSpy}],
            declarations: [DeliveryPlanComponent]
        })
            .compileComponents();

        serviceSpy.getDeliveryPlans.and.returnValue(of(expectedDeliveryPlans));
        serviceSpy.getTotalRecords.and.returnValue(of(2));

        fixture = TestBed.createComponent(DeliveryPlanComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getDeliveryPlans should return expected delivery plans', () => {
        expect(component).toBeTruthy();

        expect(component.deliveryplans).toBeDefined();
        expect(component.deliveryplans.length).toBeGreaterThan(0);
        expect(component.deliveryplans[0]).toEqual(expectedDeliveryPlans[0]);
    });
});
