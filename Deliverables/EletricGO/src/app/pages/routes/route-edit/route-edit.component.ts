import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';

import {RouteMap} from 'src/mappers/RouteMap';

import {IWarehouseDto} from 'src/dtos/warehouse/IWarehouseDto';
import {RouteDTO} from 'src/dtos/routes/routeDto';

import {IRoute} from 'src/model/routes/IRoute';

import {RouteService} from 'src/services/route.service';
import {WarehouseService} from 'src/services/warehouse.service';
import { HttpErrorResponse } from '@angular/common/http';
import { GlobalService } from 'src/services/global.service';

@Component({
    selector: 'app-route-edit',
    templateUrl: './route-edit.component.html',
    styleUrls: ['./route-edit.component.css']
})
export class RouteEditComponent implements OnInit {

    routeModel: IRoute | undefined;
    warehouses: Array<IWarehouseDto> = [];

    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";
    id = "";

    constructor(
        private route: ActivatedRoute,
        private routeService: RouteService,
        private warehousesService: WarehouseService,
        public global: GlobalService
    ) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.getRoute();
        this.getWarehouses();
    }

    getRoute(): void {
        this.id = this.route.snapshot.paramMap.get('id')!;

        if (this.id) {
            this.routeService.getRoute(this.id).subscribe((r: RouteDTO) => {
                this.routeModel = RouteMap.toModel(r);
            });
        } else {
            this.routeModel = {} as IRoute;
        }
    }

    getWarehouses(): void {
        this.warehousesService.getActiveWarehouses().subscribe((data: IWarehouseDto[]) => {
            data.forEach(w => {
                this.warehouses.push(w);
            });
        });
    }

    public create(form: NgForm) {
        this.routeService.createRoute({
            routeId: form.value.inputRouteId,
            idDepartureWarehouse: form.value.idDepartureWarehouse,
            idArrivalWarehouse: form.value.idArrivalWarehouse,
            distance: form.value.distance,
            time: form.value.time,
            energy: form.value.energy,
            extraTime: form.value.extraTime
        }).subscribe({
            error: (error: HttpErrorResponse) => {
                this.errorMessage = error.error.errors.message;
                this.success = false;
            },
            complete: () => {
                this.success = true;
                this.successMessage = 'The route was created.'
                form.resetForm();
            }
        });
    }

    public edit(form: NgForm) {
        this.routeService.updateRoute(this.id, {
            routeId: this.id,
            idDepartureWarehouse: form.value.idDepartureWarehouse,
            idArrivalWarehouse: form.value.idArrivalWarehouse,
            distance: form.value.distance,
            time: form.value.time,
            energy: form.value.energy,
            extraTime: form.value.extraTime
        }).subscribe({
            error: (error: HttpErrorResponse) => {
                this.errorMessage = error.error.errors.message;
                this.success = false;
            },
            complete: () => {
                this.success = true;
                this.successMessage = `The route ${this.id} was updated.`
                form.resetForm();
            }
        });
    }
}
