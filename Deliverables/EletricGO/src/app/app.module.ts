import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DeliveriesComponent } from './pages/deliveries/deliveries.component';
import { PackagingsComponent } from './pages/packagings/packagings.component';
import { DeliveryDetailComponent } from './pages/delivery-detail/delivery-detail.component';
import { TrucksComponent } from './pages/trucks/trucks.component';
import { PackagingDetailComponent } from './pages/packaging-detail/packaging-detail.component';
import { WarehousesComponent } from "./pages/warehouses/warehouses.component";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { HeaderComponent } from './shared/header/header.component';
import { SidenavComponent } from './shared/sidenav/sidenav.component';
import { RecordListRecordComponent } from './shared/record-list-record/record-list-record.component';
import { RecordListComponent } from './shared/record-list/record-list.component';
import { GenericFormContainerComponent } from './shared/generic-form-container/generic-form-container.component';
import { RoutesComponent } from './pages/routes/routes.component';
import { MapComponent } from './pages/map/map.component';
import { WarehouseDetailComponent } from './pages/warehouse-detail/warehouse-detail.component';
import { RouteSearchComponent } from './pages/routes/route-search/route-search.component';
import { RouteEditComponent } from './pages/routes/route-edit/route-edit.component';
import { TruckDetailComponent } from './pages/truck-detail/truck-detail.component';
import { DeliveryPlanComponent } from './pages/delivery-plan/delivery-plan.component';
import { InputMaskModule } from "@ngneat/input-mask";
import { PaginatorComponent } from './shared/paginator/paginator.component';
import { LoginComponent } from './pages/login/login.component';
import { CookieService } from 'ngx-cookie-service';
import { AuthGuard } from 'src/services/AuthGuard';
import { UsersComponent } from './pages/users/users.component';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { UserPasswordComponent } from './pages/user-password/user-password.component';
import { UserDeleteComponent } from './pages/user-delete/user-delete.component';
import { EventAggregatorService } from 'src/services/event-aggregator.service';
import {AuthInterceptor} from "./shared/auth.interceptor";
import {TokenService} from "src/services/TokenService";
import { TwoFactorAuthComponent } from './pages/two-factor-auth/two-factor-auth.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    DeliveriesComponent,
    PackagingsComponent,
    DeliveryDetailComponent,
    TrucksComponent,
    RecordListRecordComponent,
    RecordListComponent,
    PackagingDetailComponent,
    WarehousesComponent,
    HomepageComponent,
    HeaderComponent,
    SidenavComponent,
    GenericFormContainerComponent,
    RoutesComponent,
    MapComponent,
    WarehouseDetailComponent,
    RouteSearchComponent,
    RouteEditComponent,
    TruckDetailComponent,
    DeliveryPlanComponent,
    PaginatorComponent,
    LoginComponent,
    UsersComponent,
    UserDetailComponent,
    UserPasswordComponent,
    UserDeleteComponent,
    TwoFactorAuthComponent
  ],
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MaterialModule,
        ReactiveFormsModule,
        InputMaskModule,
        MatDialogModule
    ],
  providers:[
    CookieService,
    AuthGuard,
    EventAggregatorService,
    TokenService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
