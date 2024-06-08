import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Subject, takeUntil} from 'rxjs';

import {IWarehouse} from 'src/model/IWarehouse';
import { GlobalService } from 'src/services/global.service';
import {WarehouseService} from "../../../services/warehouse.service";

@Component({
    selector: 'app-warehouses',
    templateUrl: './warehouses.component.html',
    styleUrls: ['./warehouses.component.css']
})
export class WarehousesComponent implements OnInit, OnDestroy, AfterViewInit {
    warehouses: IWarehouse[] = [];
    displayedColumns: string[] = ['identifier','designation','streetName','doorNumber','city','country','zipCode','latitude','longitude','active', 'actions'];
    dataSource = new MatTableDataSource<IWarehouse>;
    destroy$: Subject<boolean> = new Subject<boolean>();

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    //Filter
    filterIdentifier?: string;
    filterDesignation?: string;
    filterStreetName?: string;
    filterDoorNumber?: number;
    filterCity?: string;
    filterCountry?: string;
    filterZipCode?: string;
    filterLatitude?: number;
    filterLongitude?: number;
    filterActive?: boolean;

    filters: any = {
        identifier: this.filterIdentifier,
        designation: this.filterDesignation,
        streetName: this.filterStreetName,
        doorNumber: this.filterDoorNumber,
        city: this.filterCity,
        country: this.filterCountry,
        zipCode: this.filterZipCode,
        latitude: this.filterLatitude,
        longitude: this.filterLongitude,
        active: this.filterActive
    }

    constructor(
        private warehouseService: WarehouseService,
        public dialog: MatDialog,
        public global: GlobalService
    ) { }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.warehouseService.getWarehouses().subscribe(data => this.totalRecords = data.length);
    }

    ngAfterViewInit() {
        this.getWarehouses();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    getWarehouses(): void {
        this.warehouseService.getFilterWarehouses(this.filters, this.sort.active, this.sort.direction, this.paginator?.pageIndex ?? 0, this.paginator?.pageSize ?? 5)
            .pipe(takeUntil(this.destroy$))
            .subscribe(data => {
                this.warehouses = data;
                this.dataSource = new MatTableDataSource(this.warehouses);
            })
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    updateValue() {
        this.filters.identifier = this.filterIdentifier;
        this.filters.designation = this.filterDesignation;
        this.filters.streetName = this.filterStreetName;
        this.filters.doorNumber = this.filterDoorNumber;
        this.filters.city = this.filterCity;
        this.filters.country = this.filterCountry;
        this.filters.zipCode = this.filterZipCode;
        this.filters.latitude = this.filterLatitude;
        this.filters.longitude = this.filterLongitude;
        this.filters.active = this.filterActive;
        this.getWarehouses();
    }

    pageChanged(e: PageEvent) {
        this.getWarehouses();
    }

    customSort(e: Sort) {
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getWarehouses();
    }
}
