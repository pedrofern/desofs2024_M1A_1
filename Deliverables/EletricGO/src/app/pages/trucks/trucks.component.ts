import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Subject, takeUntil} from 'rxjs';
import {TruckMap} from 'src/mappers/TruckMap';
import ITruck from 'src/model/ITruck';
import {GlobalService} from 'src/services/global.service';
import {TruckService} from 'src/services/truck.service';
import {TokenService} from "../../../services/TokenService";

@Component({
    selector: 'app-trucks',
    templateUrl: './trucks.component.html',
    styleUrls: ['./trucks.component.css']
})
export class TrucksComponent implements OnInit, OnDestroy, AfterViewInit {

    trucks: ITruck[] = [];
    displayedColumns: string[] = ['truckId', 'tare', 'loadCapacity', 'active', 'maximumBattery', 'autonomy', 'chargingTime', 'actions'];
    dataSource = new MatTableDataSource<ITruck>;
    destroy$: Subject<boolean> = new Subject<boolean>();

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filters
    filterTruckId?: number;
    filterTare?: number;
    filterLoadCapacity?: number;
    filterActive?: boolean;
    filterBatteryId?: number;
    filterMaximumBattery?: number;
    filterAutonomy?: number;
    filterChargingTime?: number;

    filters: any = {
        truckId: this.filterTruckId,
        tare: this.filterTare,
        loadCapacity: this.filterLoadCapacity,
        active: this.filterActive,
        batteryId: this.filterBatteryId,
        maximumBattery: this.filterMaximumBattery,
        autonomy: this.filterAutonomy,
        chargingTime: this.filterChargingTime
    }

    constructor(
        private truckService: TruckService,
        public dialog: MatDialog,
        public global: GlobalService,
        public token: TokenService
    ) {
    }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.truckService.getTotalRecords().subscribe(data => this.totalRecords = data.length);
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
        this.filters.active = this.filterActive;
        this.filters.batteryId = this.filterBatteryId;
        this.filters.maximumBattery = this.filterMaximumBattery;
        this.filters.autonomy = this.filterAutonomy;
        this.filters.chargingTime = this.filterChargingTime;
        this.getTrucks();
    }

    pageChanged(e: PageEvent) {
        this.getTrucks();
    }

    customSort(e: Sort) {
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getTrucks();
    }

    validateRole(): boolean {
        return this.token.getRole() === 'ADMIN' || this.token.getRole() === 'FLEET_MANAGER';
    }
}

