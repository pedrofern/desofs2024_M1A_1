import { HttpErrorResponse } from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {IDeliveryDto} from 'src/dtos/delivery/IDeliveryDto';
import {IPackagingDTO} from 'src/dtos/packaging/IPackagingDTO';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
import {PackagingMap} from 'src/mappers/PackagingMap';
import {IDelivery} from 'src/model/IDelivery';
import {IPackaging} from 'src/model/IPackaging';
import ITruck from 'src/model/ITruck';
import {DeliveryService} from 'src/services/delivery.service';
import { GlobalService } from 'src/services/global.service';
import {PackagingService} from 'src/services/packaging.service';
import {TruckService} from 'src/services/truck.service';

@Component({
    selector: 'app-packaging-detail',
    templateUrl: './packaging-detail.component.html',
    styleUrls: ['./packaging-detail.component.css']
})
export class PackagingDetailComponent implements OnInit {

    packaging: IPackaging | undefined;
    deliveries: Array<IDelivery> = [];
    trucks: Array<ITruck> = [];

    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";
    id = "";

    constructor(
        private route: ActivatedRoute,
        private packagingService: PackagingService,
        private deliveryService: DeliveryService,
        private truckService: TruckService,
        public global: GlobalService
    ) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.getPackaging();
        this.getDeliveries();
        this.getTrucks();
    }

    getPackaging(): void {
        this.id = this.route.snapshot.paramMap.get('id')!;
        if (this.id) {
            this.packagingService.getPackaging(this.id)
                .subscribe((data: IPackagingDTO) => this.packaging = PackagingMap.toModel(data));
        } else {
            this.packaging = {} as IPackaging;
        }
    }

    getTrucks(): void {
        this.truckService.getActiveTrucks()
            .subscribe((data: ITruckDTO[]) => {
                this.trucks = data
            });
    }

    getDeliveries() {
        this.deliveryService.getDeliveries()
            .subscribe((data: IDeliveryDto[]) => this.deliveries = data);
    }

    create(form: NgForm) {
        this.packagingService.createPackaging({
            packagingId: form.value.inputPackagingId,
            deliveryId: form.value.inputDelivery,
            truckId: form.value.inputTruck,
            x: form.value.inputX,
            y: form.value.inputY,
            z: form.value.inputZ,
            loadTime: form.value.inputLoadTime,
            unloadTime: form.value.inputUnloadTime
        })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The packaging was created'
                    form.resetForm();
                },
            })
    }

    edit(form: NgForm) {
        this.packagingService.editPackaging(
            this.id,
            {
                deliveryId: form.value.inputDelivery,
                truckId: form.value.inputTruck,
                x: form.value.inputX,
                y: form.value.inputY,
                z: form.value.inputZ,
                loadTime: form.value.inputLoadTime,
                unloadTime: form.value.inputUnloadTime
            })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The packaging ' + this.id + ' was updated'
                },
            })
    }
}
