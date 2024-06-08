import {Component, OnInit} from '@angular/core';
import {takeUntil} from 'rxjs';
import {Subject} from 'rxjs';
import {IDeliveryDto} from "../../../dtos/delivery/IDeliveryDto";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {DeliveryPlanService} from "../../../services/delivery-plan.service";
import {IDeliveryPlanDTO} from "../../../dtos/deliveryplan/IDeliveryPlanDTO";
import {IRouteDTO} from "../../../model/routes/IRouteDTO";
import { TokenService } from 'src/services/TokenService';

@Component({
    selector: 'app-delivery-plan',
    templateUrl: './delivery-plan.component.html',
    styleUrls: ['./delivery-plan.component.css']
})
export class DeliveryPlanComponent implements OnInit {
    deliveries: IDeliveryDto[] = [];
    warehouses: IWarehouseDto[] = [];
    routes: IRouteDTO[] = [];

    selectedWarehouseId!: number;
    selectedDate!: Date;

    destroy$: Subject<boolean> = new Subject<boolean>();
    displayedColumnsDeliveries: string[] = ['deliveryId', 'deliveryDate', 'weight', 'warehouseId'];
    displayedColumnsRoutes: string[] = ['routeId', 'idDepartureWarehouse', 'idArrivalWarehouse', 'distance', 'time', 'energy', 'extraTime'];

    constructor(
        private deliveryPlanService: DeliveryPlanService,
        public token: TokenService
    ) {
    }

    ngOnInit() {
        this.loadWarehouses();
    }

    loadWarehouses() {
        this.deliveryPlanService.getWarehouses()
            .pipe(takeUntil(this.destroy$))
            .subscribe((data: IWarehouseDto[]) => {
                this.warehouses = data;
            });
    }

    viewPlan() {
        const formattedDate = this.selectedDate.toISOString().split('T')[0]; // Format date to yyyy-MM-dd
        this.deliveryPlanService.getDeliveryPlan(formattedDate, this.selectedWarehouseId)
            .pipe(takeUntil(this.destroy$))
            .subscribe((data: IDeliveryPlanDTO) => {
                this.deliveries = data.deliveries;
                this.routes = data.routes;
            }, error => {
                console.error('Error fetching delivery plan:', error);
            });
    }

    downloadPdf() {
        const formattedDate = this.selectedDate.toISOString().split('T')[0]; // Format date to yyyy-MM-dd
        this.deliveryPlanService.downloadPdf(formattedDate, this.selectedWarehouseId)
            .pipe(takeUntil(this.destroy$))
            .subscribe(response => {
                const file = new Blob([response], {type: 'application/pdf'});
                const fileURL = URL.createObjectURL(file);
                const a = document.createElement('a');
                a.href = fileURL;
                a.download = 'deliveryPlan.pdf';
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
            }, error => {
                console.error('Error downloading PDF:', error);
            });
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    validateRole(): boolean {
        return this.token.getRole() === 'ADMIN' || this.token.getRole() === 'WAREHOUSE_MANAGER';
    }
}
