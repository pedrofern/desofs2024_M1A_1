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
    id = 0;

    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";

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
        this.id = Number.parseInt(this.route.snapshot.paramMap.get('id')!);
        if (this.id) {
            this.truckService.getTruck(this.id)
                .subscribe((dto: ITruckDTO) => this.truck = TruckMap.toModel(dto));
        } else {
            this.truck = {} as ITruck;
        }
    }

    create(form: NgForm): void {
        this.truckService.addTruck({
            truckId: form.value.truckId,
            tare: form.value.tare,
            loadCapacity: form.value.loadCapacity,
            active: form.value.active,
            battery: {
                batteryId: form.value.batteryId,
                maximumBattery: form.value.maximumBattery,
                autonomy: form.value.autonomy,
                chargingTime: form.value.chargingTime
            }
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
                truckId: this.id,
                tare: form.value.tare,
                loadCapacity: form.value.loadCapacity,
                active: form.value.active,
                battery: {
                    batteryId: form.value.batteryId,
                    maximumBattery: form.value.maximumBattery,
                    autonomy: form.value.autonomy,
                    chargingTime: form.value.chargingTime
                }
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
