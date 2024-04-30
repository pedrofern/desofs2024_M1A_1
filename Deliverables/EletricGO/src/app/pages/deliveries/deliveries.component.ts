import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subject, takeUntil } from 'rxjs';

import { DeliveryService } from '../../../services/delivery.service';
import { IDelivery } from 'src/model/IDelivery';
import { DeliveryMap } from 'src/mappers/DeliveryMap';
import { upperCase } from 'cypress/types/lodash';
import { GlobalService } from 'src/services/global.service';

@Component({
    selector: 'app-deliveries',
    templateUrl: './deliveries.component.html',
    styleUrls: ['./deliveries.component.css']
})
export class DeliveriesComponent implements OnInit, OnDestroy, AfterViewInit {
    destroy$: Subject<boolean> = new Subject<boolean>();
    displayedColumns: string[] = ['identifier', 'DeliveryDate_date', 'weight', 'warehouseId', 'actions'];
    deliveries: IDelivery[] = [];
    dataSource = new MatTableDataSource<IDelivery>;

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filter
    filterDeliveryId?: number;
    filterDeliveryDate?: string;
    filterWeight?: number;
    filterWarehouseId?: string;

    filters: any = {
        deliveryIdentifier: this.filterDeliveryDate,
        deliveryDate: this.filterDeliveryDate,
        weight: this.filterWeight,
        warehouseId: this.filterWarehouseId,
    }

    constructor(
        private deliveryService: DeliveryService,
        public dialog: MatDialog,
        public global: GlobalService
    ) { }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.deliveryService.getTotalRecords().subscribe(data => this.totalRecords = data.length);
    }

    ngAfterViewInit() {
        this.getDeliveries();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    getDeliveries(): void {
        this.deliveryService.getFilterDeliveries(this.filters, this.sort.active, this.sort.direction, this.paginator?.pageIndex ?? 0, this.paginator?.pageSize ?? 5)
            .pipe(takeUntil(this.destroy$))
            .subscribe(response => {
                this.deliveries = DeliveryMap.toList(response);
                this.dataSource = new MatTableDataSource(this.deliveries);
            })
    }

    updateValue() {
        this.filters.deliveryIdentifier = this.filterDeliveryId;
        this.filters.deliveryDate = this.filterDeliveryDate;
        this.filters.weight = this.filterWeight;
        this.filters.warehouseId = this.filterWarehouseId;
        this.getDeliveries();
    }

    pageChanged(e: PageEvent) {
        console.log(e);
        this.getDeliveries();
    }

    customSort(e: Sort) {
        console.log(e);
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getDeliveries();
    }
}
