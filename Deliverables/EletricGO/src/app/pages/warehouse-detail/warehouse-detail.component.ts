import { HttpErrorResponse } from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';

import {IWarehouse} from 'src/model/IWarehouse';
import { GlobalService } from 'src/services/global.service';
import {WarehouseService} from 'src/services/warehouse.service';


@Component({
    selector: 'app-warehouse-detail',
    templateUrl: './warehouse-detail.component.html',
    styleUrls: ['./warehouse-detail.component.css']
})
export class WarehouseDetailComponent implements OnInit {

    warehouse!: IWarehouse;
    success!: boolean;
    errorMessage = "";
    successMessage = "";
    id = "";

    constructor(
        private route: ActivatedRoute,
        private warehouseService: WarehouseService,
        public global: GlobalService
    ) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.getWarehouse();
    }

    getWarehouse(): void {
        this.id = this.route.snapshot.paramMap.get('id')!;
        if (this.id) {
            this.warehouseService.getWarehouse(this.id)
                .subscribe((data: IWarehouse) => this.warehouse = data);
        } else {
            this.warehouse = {} as IWarehouse;
        }
    }

    create(form: NgForm) {
        this.warehouseService.addWarehouse({
            identifier: form.value.inputWarehouseId,
            designation: form.value.inputDesignation,
            streetName: form.value.inputStreetName,
            doorNumber: form.value.inputDoorNumber,
            city: form.value.inputCity,
            country: form.value.inputCountry,
            zipCode: form.value.inputZipCode,
            latitude: form.value.inputLatitude,
            longitude: form.value.inputLongitude,
            active: form.value.inputActive
        })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.error.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The warehouse was created!'
                    form.resetForm();
                },
            })
    }

    edit(form: NgForm) {
        this.warehouseService.editWarehouse(
            this.id,
            {
                designation: form.value.inputDesignation,
                streetName: form.value.inputStreetName,
                doorNumber: form.value.inputDoorNumber,
                city: form.value.inputCity,
                country: form.value.inputCountry,
                zipCode: form.value.inputZipCode,
                latitude: form.value.inputLatitude,
                longitude: form.value.inputLongitude,
                active: form.value.inputActive
            })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.error.errors.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The warehouse ' + this.id + ' was updated'
                },
            })
    }
}
