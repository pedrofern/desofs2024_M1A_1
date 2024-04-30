import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {of} from 'rxjs';
import {IDeliveryDto} from 'src/dtos/delivery/IDeliveryDto';
import {DeliveryService} from 'src/services/delivery.service';
import {DeliveriesComponent} from './deliveries.component';

const expectedDeliveries: IDeliveryDto[] =
    [{deliveryIdentifier: 1, deliveryDate: '12-12-2022', weight: 100, warehouseId: 'M99'},
        {deliveryIdentifier: 2, deliveryDate: '12-12-2022', weight: 200, warehouseId: 'M98'}];

describe('DeliveriesComponent', () => {
    let component: DeliveriesComponent;
    let fixture: ComponentFixture<DeliveriesComponent>;

    beforeEach(async () => {
        const serviceSpy = jasmine.createSpyObj('DeliveryService', ['getDeliveries', 'getTotalRecords', 'getFilterDeliveries']);

        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MatDialogModule,
                MatSortModule,
                MatPaginatorModule,
                BrowserAnimationsModule
            ],
            providers: [{provide: MatDialogRef, useValue: {}}, {provide: DeliveryService, useValue: serviceSpy}],
            declarations: [DeliveriesComponent]
        })
            .compileComponents();

        serviceSpy.getTotalRecords.and.returnValue(of(expectedDeliveries));
        serviceSpy.getFilterDeliveries.and.returnValue(of(expectedDeliveries));
        serviceSpy.getDeliveries.and.returnValue(of(expectedDeliveries));

        fixture = TestBed.createComponent(DeliveriesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('getDeliveries should return expected deliveries', () => {
        expect(component).toBeTruthy();

        expect(component.deliveries).toBeDefined();
        expect(component.deliveries.length).toBeGreaterThan(0);
        expect(component.deliveries[0]).toEqual(expectedDeliveries[0]);
    });
});
