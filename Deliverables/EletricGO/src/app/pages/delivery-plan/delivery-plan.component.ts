import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Subject, takeUntil} from 'rxjs';
import {DeliveryPlanMap} from 'src/mappers/DeliveryPlanMap';
import {IDeliveryPlan} from 'src/model/IDeliveryPlan';
import { GlobalService } from 'src/services/global.service';
import {DeliveryPlanService} from "../../../services/delivery-plan.service";

@Component({
    selector: 'app-delivery-plan',
    templateUrl: './delivery-plan.component.html',
    styleUrls: ['./delivery-plan.component.css']
})
export class DeliveryPlanComponent implements OnInit, OnDestroy, AfterViewInit {

    deliveryplans: IDeliveryPlan[] = [];
    displayedColumns: string[] = ['truckId', 'date', 'deliveriesId', 'paths', 'cost'];

    dataSource = new MatTableDataSource<IDeliveryPlan>;
    destroy$: Subject<boolean> = new Subject<boolean>();

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filter
    filterTruckId?: string;
    filterDate?: string;
    filterDeliveriesId?: number[];
    filterPaths?: string[];
    filterCost?: number;

    filters: any = {
        truckId: this.filterTruckId,
        date: this.filterDate,
        deliveriesId: this.filterDeliveriesId,
        paths: this.filterPaths,
        cost: this.filterCost
    }

    constructor(
        private deliveryPlanService: DeliveryPlanService,
        public dialog: MatDialog,
        public global: GlobalService
        
    ) {
    }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.deliveryPlanService.getTotalRecords().subscribe(count => this.totalRecords = count);
    }

    ngAfterViewInit() {
        this.getDeliveriesPlan();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    getDeliveriesPlan(): void {
        this.deliveryPlanService.getDeliveryPlans(this.filters, this.sort.active, this.sort.direction, this.paginator?.pageIndex ?? 0, this.paginator?.pageSize ?? 5)
            .pipe(takeUntil(this.destroy$))
            .subscribe(data => {
                this.deliveryplans = DeliveryPlanMap.toList(data);
                this.dataSource = new MatTableDataSource(this.deliveryplans);
            })
    }

    updateValue() {
        this.filters.truckId = this.filterTruckId;
        this.filters.date = this.filterDate;
        this.filters.deliveriesId = this.filterDeliveriesId;
        this.filters.paths = this.filterPaths;
        this.filters.cost = this.filterCost;
        this.getDeliveriesPlan();
    }

    pageChanged(e: PageEvent) {
        console.log(e);
        this.getDeliveriesPlan();
    }

    customSort(e: Sort) {
        console.log(e);
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getDeliveriesPlan();
    }
}
