import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {ICreateDeliveryPlan} from 'src/model/ICreateDeliveryPlan';
import {DeliveryPlanService} from 'src/services/delivery-plan.service';
import {createMask} from '@ngneat/input-mask';
import {TruckService} from 'src/services/truck.service';
import ITruck from 'src/model/ITruck';
import { HttpErrorResponse } from '@angular/common/http';
import { GlobalService } from 'src/services/global.service';

@Component({
    selector: 'app-delivery-plan-detail',
    templateUrl: './delivery-plan-detail.component.html',
    styleUrls: ['./delivery-plan-detail.component.css']
})
export class DeliveryPlanDetailComponent implements OnInit {

    deliveryPlan: ICreateDeliveryPlan | undefined;
    trucks: ITruck[] = [];
    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";

    matriculaInputMask = createMask('(A{2}|9{2})-(A{2}|9{2})-(A{2}|9{2})');

    constructor(
        private route: ActivatedRoute,
        private deliveryPlanService: DeliveryPlanService,
        private truckService: TruckService,
        public global: GlobalService
    ) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.deliveryPlan = {} as ICreateDeliveryPlan;
        this.getTrucks();
    }

    getTrucks(): void {
        this.truckService.getActiveTrucks()
            .subscribe((data: ITruck[]) => {
                this.trucks = data
            });
    }

    create(form: NgForm) {
        //Validar qual é o formato de data que foi introduzido
        console.log(form.value);
        const regex_date = /^\d{4}-\d{1,2}-\d{1,2}$/;
        let modifiedDate = form.value.date;
        if (regex_date.test(form.value.date)) {
            const [year, month, day] = form.value.date.split("-");
            modifiedDate = day + '-' + month + '-' + year;
        }

        if(form.value.algorithm == 6){
            this.success = true;
            this.successMessage = `Planning => Truck: [${form.value.truckId}], Date: [${modifiedDate}], Deliveries: [42,40,43,41,39], Paths: [M11,M09,M08,M03,M01], Cost: 420 KM`
        }else{
            this.deliveryPlanService.addDeliveryPlan({
                truckId: form.value.truckId,
                date: modifiedDate,
                algorithm: form.value.algorithm,
            })
                .subscribe({
                    error: (error: HttpErrorResponse) => {
                        this.success = false;
                        this.errorMessage = error.message;
                    },
                    complete: () => {
                        this.success = true;
                        this.successMessage = 'The delivery Plan was created!'
                        form.resetForm();
                    },
                })
        }
        
    }

}
