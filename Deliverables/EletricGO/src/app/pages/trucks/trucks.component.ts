import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subject, takeUntil } from 'rxjs';
import { TruckMap } from 'src/mappers/TruckMap';
import ITruck from 'src/model/ITruck';
import { GlobalService } from 'src/services/global.service';
import { TruckService } from 'src/services/truck.service';

@Component({
    selector: 'app-trucks',
    templateUrl: './trucks.component.html',
    styleUrls: ['./trucks.component.css']
})
export class TrucksComponent implements OnInit, OnDestroy, AfterViewInit {

    trucks: ITruck[] = [];
    displayedColumns: string[] = ['truckId', 'tare', 'loadCapacity', 'maximumBattery', 'autonomy', 'chargingTime', 'active', 'actions'];
    dataSource = new MatTableDataSource<ITruck>;
    destroy$: Subject<boolean> = new Subject<boolean>();

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filters
    filterTruckId?: string;
    filterTare?: number;
    filterLoadCapacity?: number;
    filterMaximumBattery?: number;
    filterAutonomy?: number;
    filterChargingTime?: number;
    filterActive?: boolean;

    filters: any = {
        truckId: this.filterTruckId,
        tare: this.filterTare,
        loadCapacity: this.filterLoadCapacity,
        maximumBattery: this.filterMaximumBattery,
        autonomy: this.filterAutonomy,
        chargingTime: this.filterChargingTime,
        active: this.filterActive
    }

    constructor(
        private truckService: TruckService,
        public dialog: MatDialog,
        public global: GlobalService
    ) {
    }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.truckService.getTotalRecords().subscribe(count => this.totalRecords = count);
    }

    ngAfterViewInit() {
        this.getTrucks();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    getTrucks(): void {
        this.truckService.getTrucks(this.filters, this.sort.active, this.sort.direction, this.paginator?.pageIndex ?? 0, this.paginator?.pageSize ?? 5)
            .pipe(takeUntil(this.destroy$))
            .subscribe(list => {
                this.trucks = TruckMap.toModelList(list);
                this.dataSource = new MatTableDataSource(this.trucks);
            })
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    updateValue() {
        this.filters.truckId = this.filterTruckId;
        this.filters.tare = this.filterTare;
        this.filters.loadCapacity = this.filterLoadCapacity;
        this.filters.maximumBattery = this.filterMaximumBattery;
        this.filters.autonomy = this.filterAutonomy;
        this.filters.chargingTime = this.filterChargingTime;
        this.filters.active = this.filterActive;
        this.getTrucks();
    }

    pageChanged(e: PageEvent) {
        console.log(e);
        this.getTrucks();
    }

    customSort(e: Sort) {
        console.log(e);
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getTrucks();
    }
}

