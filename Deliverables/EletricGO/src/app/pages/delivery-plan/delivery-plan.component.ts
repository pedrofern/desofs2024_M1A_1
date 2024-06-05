import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';

import { takeUntil } from 'rxjs';
import { Subject } from 'rxjs';
import {IDeliveryDto} from "../../../dtos/delivery/IDeliveryDto";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {DeliveryPlanService} from "../../../services/delivery-plan.service";

@Component({
    selector: 'app-delivery-plan',
    templateUrl: './delivery-plan.component.html',
    styleUrls: ['./delivery-plan.component.css']
})
export class DeliveryPlanComponent implements OnInit {
    deliveries: IDeliveryDto[] = [];
    warehouses: IWarehouseDto[] = [];

    selectedDeliveryId!: number;
    selectedWarehouseId!: number;
    pdfSrc!: string;

    destroy$: Subject<boolean> = new Subject<boolean>();

    constructor(
        private deliveryPlanService: DeliveryPlanService,
        private sanitizer: DomSanitizer,
    ) {}

    ngOnInit() {
        this.loadDeliveries();
        this.loadWarehouses();
    }

    loadDeliveries() {
        this.deliveryPlanService.getDeliveries()
            .pipe(takeUntil(this.destroy$))
            .subscribe((data: IDeliveryDto[]) => {
                this.deliveries = data;
            });
    }

    loadWarehouses() {
        this.deliveryPlanService.getWarehouses()
            .pipe(takeUntil(this.destroy$))
            .subscribe((data: IWarehouseDto[]) => {
                this.warehouses = data;
            });
    }

    onSubmit() {
        this.deliveryPlanService.downloadPdf(this.selectedDeliveryId, this.selectedWarehouseId)
            .pipe(takeUntil(this.destroy$))
            .subscribe(response => {
                const file = new Blob([response], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                this.pdfSrc = this.sanitizer.bypassSecurityTrustResourceUrl(fileURL).toString();
            }, error => {
                console.error('Error fetching PDF:', error);
            });
    }

    downloadPdf() {
        this.deliveryPlanService.downloadPdf(this.selectedDeliveryId, this.selectedWarehouseId)
            .pipe(takeUntil(this.destroy$))
            .subscribe(response => {
                const file = new Blob([response], { type: 'application/pdf' });
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
}
