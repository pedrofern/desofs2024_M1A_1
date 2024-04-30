import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DeliveriesComponent} from './pages/deliveries/deliveries.component';
import {DeliveryDetailComponent} from './pages/delivery-detail/delivery-detail.component';
import {HomepageComponent} from './pages/homepage/homepage.component';
import {PackagingDetailComponent} from './pages/packaging-detail/packaging-detail.component';
import {PackagingsComponent} from './pages/packagings/packagings.component';
import {TrucksComponent} from './pages/trucks/trucks.component';
import {TruckDetailComponent} from './pages/truck-detail/truck-detail.component';
import {WarehousesComponent} from './pages/warehouses/warehouses.component';
import {RouteSearchComponent} from './pages/routes/route-search/route-search.component';
import {RouteEditComponent} from './pages/routes/route-edit/route-edit.component';
import {MapComponent} from "./pages/map/map.component";
import {WarehouseDetailComponent} from './pages/warehouse-detail/warehouse-detail.component';
import {DeliveryPlanComponent} from './pages/delivery-plan/delivery-plan.component';
import {DeliveryPlanDetailComponent} from './pages/delivery-plan-detail/delivery-plan-detail.component';
import { LoginComponent } from './pages/login/login.component';
import { LoginService } from 'src/services/login.service';
import { AuthGuard } from 'src/services/AuthGuard';
import { UsersComponent } from './pages/users/users.component';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { UserPasswordComponent } from './pages/user-password/user-password.component';
import { UserDeleteComponent } from './pages/user-delete/user-delete.component';

const routes: Routes = [
    {path: 'login', component: LoginComponent, pathMatch: 'full'},
    {path: '', component: HomepageComponent, canActivate: [AuthGuard]},
    {path: 'deliveries', component: DeliveriesComponent, canActivate: [AuthGuard]},
    {path: 'deliveries/edit/:id', component: DeliveryDetailComponent, canActivate: [AuthGuard]},
    {path: 'deliveries/create', component: DeliveryDetailComponent, canActivate: [AuthGuard]},
    {path: 'packagings', component: PackagingsComponent, canActivate: [AuthGuard]},
    {path: 'packagings/edit/:id', component: PackagingDetailComponent, canActivate: [AuthGuard]},
    {path: 'packagings/create', component: PackagingDetailComponent, canActivate: [AuthGuard]},
    {path: 'trucks', component: TrucksComponent, canActivate: [AuthGuard]},
    {path: 'trucks/edit/:id', component: TruckDetailComponent, canActivate: [AuthGuard]},
    {path: 'trucks/create', component: TruckDetailComponent, canActivate: [AuthGuard]},
    {path: 'warehouses', component: WarehousesComponent, canActivate: [AuthGuard]},
    {path: 'warehouses/edit/:id', component: WarehouseDetailComponent, canActivate: [AuthGuard]},
    {path: 'warehouses/create', component: WarehouseDetailComponent, canActivate: [AuthGuard]},
    {path: 'routes', component: RouteSearchComponent, canActivate: [AuthGuard]},
    {path: 'routes/create', component: RouteEditComponent, canActivate: [AuthGuard]},
    {path: 'routes/edit/:id', component: RouteEditComponent, canActivate: [AuthGuard]},
    {path: 'delivery-plan', component: DeliveryPlanComponent, canActivate: [AuthGuard]},
    {path: 'delivery-plan-detail/create', component: DeliveryPlanDetailComponent, canActivate: [AuthGuard]},
    {path: 'map', component: MapComponent, canActivate: [AuthGuard]},
    {path: 'users', component: UsersComponent, canActivate: [AuthGuard]},
    {path: 'users/edit/:email', component: UserDetailComponent, canActivate: [AuthGuard]},
    {path: 'users/create', component: UserDetailComponent, canActivate: [AuthGuard]},
    {path: 'users/password', component: UserPasswordComponent, canActivate: [AuthGuard]},
    {path: 'users/delete/:email', component: UserDeleteComponent, canActivate: [AuthGuard]},
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
