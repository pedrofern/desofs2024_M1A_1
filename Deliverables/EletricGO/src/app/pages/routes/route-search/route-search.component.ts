import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subject, takeUntil } from 'rxjs';
import { RouteMap } from 'src/mappers/RouteMap';
import { IWarehouseDto } from 'src/dtos/warehouse/IWarehouseDto';
import { RouteDTO } from 'src/dtos/routes/routeDto';
import { IRoute } from 'src/model/routes/IRoute';
import { RouteService } from 'src/services/route.service';
import { WarehouseService } from 'src/services/warehouse.service';
import { GlobalService } from 'src/services/global.service';
@Component({
    selector: 'app-route-search',
    templateUrl: './route-search.component.html',
    styleUrls: ['./route-search.component.css']
})
export class RouteSearchComponent implements OnInit, OnDestroy, AfterViewInit {
    destroy$: Subject<boolean> = new Subject<boolean>();
    displayedColumns: string[] = ['routeId', 'idDepartureWarehouse', 'idArrivalWarehouse', 'distance', 'time', 'energy', 'extraTime', 'actions'];
    routes: IRoute[] = [];
    warehouses: IWarehouseDto[] = [];
    dataSource = new MatTableDataSource<IRoute>;

    // Pagination
    pageSizes = [5, 10, 20, 100];
    totalRecords?: number;

    // Filter
    filterRouteId?: string;
    filterIdDeparture?: string;
    filterIdArrival?: string;
    filterDistance?: number;
    filterTime?: number;
    filterEnergy?: number;
    filterExtraTime?: number;

    filters: any = {
        routeId: this.filterRouteId,
        idDepartureWarehouse: this.filterIdDeparture,
        idArrivalWarehouse: this.filterIdArrival,
        distance: this.filterDistance,
        time: this.filterTime,
        energy: this.filterEnergy,
        extraTime: this.filterExtraTime
    }

    constructor(
        private routeService: RouteService,
        private warehousesService: WarehouseService,
        public dialog: MatDialog,
        public global: GlobalService
    ) { }

    @ViewChild(MatSort) sort: MatSort = new MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.global.updateData(true);
        this.routeService.getTotalRecords().subscribe(total => { this.totalRecords = total; });
    }

    ngAfterViewInit(): void {
        this.getRoutes();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
    }

    getRoutes(): void {
        this.routeService.getRoutes(this.filters, this.sort.active, this.sort.direction, this.paginator?.pageIndex ?? 0, this.paginator?.pageSize ?? 5)
            .pipe(takeUntil(this.destroy$))
            .subscribe((data: RouteDTO[]) => {
                this.routes = RouteMap.toModelList(data);
                this.dataSource = new MatTableDataSource(this.routes);
            });
    }

    updateValue() {
        this.filters.routeId = this.filterRouteId;
        this.filters.idDepartureWarehouse = this.filterIdDeparture;
        this.filters.idArrivalWarehouse = this.filterIdArrival;
        this.filters.distance = this.filterDistance;
        this.filters.time = this.filterTime;
        this.filters.energy = this.filterEnergy;
        this.filters.extraTime = this.filterExtraTime;
        this.getRoutes();
    }

    getWarehouses(): void {
        this.warehousesService.getActiveWarehouses().subscribe((data: IWarehouseDto[]) => {
            this.warehouses = data
        });
    }

    pageChanged(e: PageEvent) {
        console.log(e);
        this.getRoutes();
    }

    customSort(e: Sort) {
        console.log(e);
        this.sort.direction = e.direction;
        this.sort.active = e.active;
        this.getRoutes();
    }
}
