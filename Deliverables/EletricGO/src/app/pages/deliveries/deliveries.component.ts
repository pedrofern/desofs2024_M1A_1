import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subject, takeUntil } from 'rxjs';

import { DeliveryService } from '../../../services/delivery.service';
import { IDelivery } from 'src/model/IDelivery';
import { DeliveryMap } from 'src/mappers/DeliveryMap';
import { GlobalService } from 'src/services/global.service';
import { TokenService } from 'src/services/TokenService';
import { IDeliveryDto } from 'src/dtos/delivery/IDeliveryDto';


@Component({
    selector: 'app-deliveries',
    templateUrl: './deliveries.component.html',
    styleUrls: ['./deliveries.component.css']
})
export class DeliveriesComponent implements OnInit, OnDestroy, AfterViewInit {
    destroy$: Subject<boolean> = new Subject<boolean>();
    displayedColumns: string[] = ['deliveryId', 'deliveryDate', 'weight', 'warehouseId', 'actions'];
    deliveries: IDelivery[] = [];
    dataSource = new MatTableDataSource<IDelivery>;

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filter
    filterDeliveryId!: number;
    filterDeliveryDate!: string;
    filterWeight!: number;
    filterWarehouseId!: string;

    filters: IDeliveryDto = {
        deliveryId: this.filterDeliveryId,
        deliveryDate: this.filterDeliveryDate,
        weight: this.filterWeight,
        warehouseId: this.filterWarehouseId,
    }

    constructor(
        private deliveryService: DeliveryService,
        public dialog: MatDialog,
        public global: GlobalService,
        public token: TokenService
    ) { }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.deliveryService.getTotalRecords().subscribe(data => this.totalRecords = data.length);
    }

    ngAfterViewInit(): void {
        this.getDeliveries();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    ngOnDestroy(): void {
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

    updateValue(): void {
        this.filters.deliveryId = this.filterDeliveryId;
        this.filters.deliveryDate = this.filterDeliveryDate;
        this.filters.weight = this.filterWeight;
        this.filters.warehouseId = this.filterWarehouseId;
        this.getDeliveries();
    }

    pageChanged(e: PageEvent): void {
        this.getDeliveries();
        console.warn(e);
    }

    customSort(e: Sort): void {
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getDeliveries();
        console.warn(e);
    }

    validateRole(): boolean {
        return this.token.getRole() === 'ADMIN' || this.token.getRole() === 'WAREHOUSE_MANAGER';
    }
}
