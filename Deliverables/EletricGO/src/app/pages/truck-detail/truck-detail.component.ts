import { HttpErrorResponse } from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {createMask} from '@ngneat/input-mask';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
import {TruckMap} from 'src/mappers/TruckMap';
import ITruck from 'src/model/ITruck';
import { GlobalService } from 'src/services/global.service';
import {TruckService} from 'src/services/truck.service';

@Component({
    selector: 'app-truck-detail',
    templateUrl: './truck-detail.component.html',
    styleUrls: ['./truck-detail.component.css']
})
export class TruckDetailComponent implements OnInit {
    
    truck: ITruck | undefined;
    id = "";

    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";

    matriculaInputMask = createMask('(A{2}|9{2})-(A{2}|9{2})-(A{2}|9{2})');

    constructor(
        private route: ActivatedRoute,
        private truckService: TruckService,
        public global: GlobalService
    ) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.getTruck();
    }

    getTruck(): void {
        this.id = this.route.snapshot.paramMap.get('id')!;
        if (this.id) {
            this.truckService.getTruck(this.id)
                .subscribe((dto: ITruckDTO) => this.truck = TruckMap.toModel(dto));
        } else {
            this.truck = {} as ITruck;
        }
    }

    create(form: NgForm): void {
        this.truckService.addTruck({
            truckId: form.value.inputTruckId,
            tare: form.value.inputTare,
            loadCapacity: form.value.inputLoadCapacity,
            maximumBattery: form.value.inputMaximumBattery,
            autonomy: form.value.inputAutonomy,
            chargingTime: form.value.inputChargingTime,
            active: form.value.inputActive
        })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.error.errors.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The truck was created!';
                    form.resetForm();
                }
            });
    }

    edit(form: NgForm) {
        this.truckService.updateTruck(
            this.id,
            {
                tare: form.value.inputTare,
                loadCapacity: form.value.inputLoadCapacity,
                maximumBattery: form.value.inputMaximumBattery,
                autonomy: form.value.inputAutonomy,
                chargingTime: form.value.inputChargingTime,
                active: form.value.inputActive
            })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.error.errors.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The truck ' + this.id + ' was updated!'
                },
            })
    }
}
