import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RouteDTO } from 'src/dtos/routes/routeDto';
import { GeneralItemDTO } from 'src/dtos/shared/generalItemDto';
import { IWarehouseDto } from 'src/dtos/warehouse/IWarehouseDto';
import { GlobalService } from 'src/services/global.service';
import { RouteService } from 'src/services/route.service';
import { WarehouseService } from 'src/services/warehouse.service';

const expectedRoutes: GeneralItemDTO[] = [
    {id: "R01", description: "First route"},
    {id: "R02", description: "Second route"},
    {id: "R03", description: "Third route"},
    {id: "R04", description: "Fourth route"}
];

@Component({
  selector: 'app-routes',
  templateUrl: './routes.component.html',
  styleUrls: ['./routes.component.css']
})
export class RoutesComponent implements OnInit {

  routes: RouteDTO[] = [];
  warehouses: IWarehouseDto[] = [];

  currentSelectedRoute?: RouteDTO = undefined;

  constructor(private routeService: RouteService, private warehousesService: WarehouseService, public global: GlobalService) { }

  ngOnInit(): void {
    this.global.updateData(true);
    this.getRoutes();
    this.getWarehouses();
  }

  onRecordClicked(id: string) {
    console.log("Route component caught click on record '" + id + "'");

    this.currentSelectedRoute = this.routes.find(r => r.routeId === id);
  }

  add(form: NgForm) {

  }

  getRoutes(): void {
    // this.routeService.getRoutes().subscribe((data: RouteDTO[]) => {
    //   this.routes = data
    // });
  }

  getWarehouses(): void {
    this.warehousesService.getActiveWarehouses().subscribe((data: IWarehouseDto[]) => {
      this.warehouses = data
    });
  }

  getRoutesAsGeneralItem(): GeneralItemDTO[] {
    let returnValue: GeneralItemDTO[] = [];

    this.routes.forEach(r => {
      returnValue.push({
        id: r.routeId,
        description: 'Departure: ' + r.idDepartureWarehouse + ' - ' + r.departureWarehouse + ' | Arrival: ' + r.idArrivalWarehouse + ' - ' + r.arrivalWarehouse
      }as GeneralItemDTO);
    });

    return returnValue;
  }

}
