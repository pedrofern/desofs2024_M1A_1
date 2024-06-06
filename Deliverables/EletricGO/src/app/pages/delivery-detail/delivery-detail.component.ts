import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {NgForm} from '@angular/forms';
import {IDelivery} from '../../../model/IDelivery';
import {DeliveryService} from '../../../services/delivery.service';
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {IWarehouse} from 'src/model/IWarehouse';
import {DeliveryMap} from 'src/mappers/DeliveryMap';
import {HttpErrorResponse} from '@angular/common/http';
import {GlobalService} from 'src/services/global.service';

@Component({
    selector: 'app-delivery-detail',
    templateUrl: './delivery-detail.component.html',
    styleUrls: ['./delivery-detail.component.css']
})
export class DeliveryDetailComponent implements OnInit {

    delivery: IDelivery | undefined;
    warehouses: IWarehouseDto[] = [];
    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";
    id = 0;

    constructor(
        private route: ActivatedRoute,
        private deliveryService: DeliveryService,
        public global: GlobalService
    ) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.getDelivery();
        this.getWarehouses();
    }

    getDelivery(): void {
        this.id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
        if (this.id) {
            this.deliveryService.getDelivery(this.id)
                .subscribe(data => this.delivery = DeliveryMap.toModel(data));
        } else {
            this.delivery = {} as IDelivery;
        }
    }

    getWarehouses(): void {
        this.deliveryService.getWarehouses()
            .subscribe((data: IWarehouse[]) => {
                this.warehouses = data
            });
    }

    onDateChange(event: any) {
        const date = new Date(event);
        if (this.delivery) {
            this.delivery.deliveryDate = date.toISOString().split('T')[0];
        }
    }

    create(form: NgForm) {
        //Validar qual é o formato de data que foi introduzido
        const regex_date = /^\d{1,2}-\d{1,2}-\d{4}$/;
        let modifiedDate = form.value.deliveryDate;
        if (regex_date.test(form.value.deliveryDate)) {
            const [year, month, day] = form.value.deliveryDate.split("-");
            modifiedDate = `${day}-${month}-${year}`;
        }

        this.deliveryService.addDelivery({
            deliveryDate: modifiedDate,
            weight: form.value.deliveryWeight,
            warehouseId: String(form.value.deliveryWarehouseId)
        })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.error.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The delivery was created!'
                    form.resetForm();
                },
            })
    }

    edit(form: NgForm) {
        const regex_date = /^\d{1,2}-\d{1,2}-\d{4}$/;
        let modifiedDate = form.value.deliveryDate;
        if (regex_date.test(form.value.deliveryDate)) {
            const [year, month, day] = form.value.deliveryDate.split("-");
            modifiedDate = `${day}-${month}-${year}`;
        }

        this.deliveryService.editDelivery(
            form.value.deliveryId,
            {
                deliveryDate: modifiedDate,  // ensure correct property name
                weight: form.value.deliveryWeight,
                warehouseId: form.value.deliveryWarehouseId // ensure correct property name
            }
        ).subscribe({
            error: (error: HttpErrorResponse) => {
                this.errorMessage = error.error;
                this.success = false;
            },
            complete: () => {
                this.success = true;
                this.successMessage = 'The delivery ' + form.value.deliveryId + ' was updated!'
            },
        });
    }
}
