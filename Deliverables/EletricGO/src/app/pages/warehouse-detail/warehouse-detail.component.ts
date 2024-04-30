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

    warehouse: IWarehouse | undefined;
    success: boolean | undefined;
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
            Identifier: form.value.inputWarehouseId,
            Designation: form.value.inputDesignation,
            StreetName: form.value.inputStreetName,
            DoorNumber: form.value.inputDoorNumber,
            City: form.value.inputCity,
            Country: form.value.inputCountry,
            ZipCode: form.value.inputZipCode,
            Latitude: form.value.inputLatitude,
            Longitude: form.value.inputLongitude,
            Height: form.value.inputHeight,
            Active: form.value.inputActive
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
                Designation: form.value.inputDesignation,
                StreetName: form.value.inputStreetName,
                DoorNumber: form.value.inputDoorNumber,
                City: form.value.inputCity,
                Country: form.value.inputCountry,
                ZipCode: form.value.inputZipCode,
                Latitude: form.value.inputLatitude,
                Longitude: form.value.inputLongitude,
                Height: form.value.inputHeight,
                Active: form.value.inputActive
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
