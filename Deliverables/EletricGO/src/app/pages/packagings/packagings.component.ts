import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Subject, takeUntil, tap } from 'rxjs';
import { PackagingMap } from 'src/mappers/PackagingMap';

import { IPackaging } from 'src/model/IPackaging';
import { GlobalService } from 'src/services/global.service';
import { PackagingService } from 'src/services/packaging.service';
@Component({
    selector: 'app-packagings',
    templateUrl: './packagings.component.html',
    styleUrls: ['./packagings.component.css']
})
export class PackagingsComponent implements OnInit, OnDestroy, AfterViewInit {
    destroy$: Subject<boolean> = new Subject<boolean>();
    displayedColumns: string[] = ['packagingId', 'deliveryId', 'truckId','loadTime', 'unloadTime', 'x', 'y', 'z', 'actions'];
    packagings: IPackaging[] = [];
    dataSource = new MatTableDataSource<IPackaging>;

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filter
    filterPackagingId?: string;
    filterDeliveryId?: number;
    filterTruckId?: number;
    filterX?: number;
    filterY?: number;
    filterZ?: number;
    filterLoadTime?: string;
    filterUnloadTime?: string;

    filters: any = {
        packagingId: this.filterPackagingId,
        deliveryId: this.filterDeliveryId,
        truckId: this.filterTruckId,
        x: this.filterX,
        y: this.filterY,
        z: this.filterZ,
        loadTime: this.filterLoadTime,
        unloadTime: this.filterUnloadTime
    }

    constructor(
        private service: PackagingService,
        public dialog: MatDialog,
        public global: GlobalService
    ) { }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.service.getTotalRecords().subscribe(data => this.totalRecords = data.length);
    }

    ngAfterViewInit() {
        this.getPackagings();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        console.log(this.packagings);
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    getPackagings(): void {
        console.log(this.packagings);
        this.service.getPackagings(this.filters, this.sort.active, this.sort.direction, this.paginator?.pageIndex ?? 0, this.paginator?.pageSize ?? 5)
            .pipe(takeUntil(this.destroy$))
            .subscribe(response => {
                this.packagings = PackagingMap.toList(response);
                this.dataSource = new MatTableDataSource(this.packagings);
            })
        console.log(this.packagings);
    }

    updateValue() {
        this.filters.deliveryId = this.filterDeliveryId;
        this.filters.packagingId = this.filterPackagingId;
        this.filters.deliveryId = this.filterDeliveryId;
        this.filters.truckId = this.filterTruckId;
        this.filters.x = this.filterX;
        this.filters.y = this.filterY;
        this.filters.z = this.filterZ;
        this.filters.loadTime = this.filterLoadTime;
        this.filters.unloadTime = this.filterUnloadTime;
        this.getPackagings();
    }

    pageChanged(e: PageEvent) {
        this.getPackagings();
    }

    customSort(e: Sort) {
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getPackagings();
    }
}
