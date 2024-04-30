import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DeliveryPlanDetailComponent} from './delivery-plan-detail.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule, NgForm} from "@angular/forms";
import {WarehouseDetailComponent} from "../warehouse-detail/warehouse-detail.component";
import {DeliveryPlanService} from "../../../services/delivery-plan.service";
import {of, throwError} from "rxjs";
import {IDeliveryPlan} from "../../../model/IDeliveryPlan";
import {ICreateDeliveryPlan} from "../../../model/ICreateDeliveryPlan";

const expectedDeliveryPlanCreate: ICreateDeliveryPlan =
    { truckId: "45-XZ-12", date: '2022-12-20', algorithm: "XYZ"};

const expectedDeliveryPlan: IDeliveryPlan =
    { deliveryPlanId: 1, truckId: "45-XZ-12", date: '2022-12-20', deliveriesId: [1,2,3], paths: ["X", "YZ", "Z"], cost: 5};

describe('DeliveryPlanDetailComponent', () => {
    let component: DeliveryPlanDetailComponent;
    let fixture: ComponentFixture<DeliveryPlanDetailComponent>;
    let serviceSpy: any

    beforeEach(async () => {
        serviceSpy = jasmine.createSpyObj('DeliveryPlanService', ['getDeliveryPlan', 'addDeliveryPlan']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MatDialogModule,
                RouterTestingModule,
                FormsModule
            ],
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: DeliveryPlanService, useValue: serviceSpy}],
            declarations: [WarehouseDetailComponent]
        })
            .compileComponents();

        serviceSpy.getDeliveryPlan.and.returnValue(of(expectedDeliveryPlan));
        serviceSpy.addDeliveryPlan.and.returnValue(of({}));

        fixture = TestBed.createComponent(DeliveryPlanDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getDeliveryPlan should return expected delivery plan', () => {
        expect(component).toBeTruthy();

        expect(component.deliveryPlan).toBeDefined();
    });

    it('createDeliveryPlan fails when it already exists', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedDeliveryPlan,
            resetForm(value?: any) {
            }
        };

        serviceSpy.addDeliveryPlan.and.returnValue(throwError(() => ({status: 500, error: {errors: {message: "Already exists"}}})));

        component.create(testForm)
        //expect(component.errorMessage).toEqual("Already exists");
    });

    it('createDeliveryPlan succeeds when it does not exist', () => {
        expect(component).toBeTruthy();

        const testForm = <NgForm>{
            value: expectedDeliveryPlan,
            resetForm(value?: any) {
            }
        };

        component.create(testForm)
        expect(component.success).toEqual(true);
    });
});
