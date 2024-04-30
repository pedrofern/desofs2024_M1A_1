import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { IPackagingDTO } from 'src/dtos/packaging/IPackagingDTO';
import { PackagingService } from 'src/services/packaging.service';
import { PackagingsComponent } from './packagings.component';

const expectedPackaging: IPackagingDTO[] =
  [
    {
      packagingId: "P01",
      deliveryId: "1",
      truckId: "AA-00-BB",
      x: 1,
      y: 2,
      z: 3,
      loadTime: 100,
      unloadTime: 200
    },
    {
      packagingId: "P02",
      deliveryId: "2",
      truckId: "AA-00-CC",
      x: 3,
      y: 4,
      z: 5,
      loadTime: 300,
      unloadTime: 400
    }
  ];

describe('PackagingsComponent', () => {
  let component: PackagingsComponent;
  let fixture: ComponentFixture<PackagingsComponent>;

  beforeEach(async () => {
    const serviceSpy = jasmine.createSpyObj('PackagingService', ['getPackagings', 'getTotalRecords']);

    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        MatDialogModule,
        MatSortModule,
        MatPaginatorModule,
        BrowserAnimationsModule
      ],
      providers: [{ provide: MatDialogRef, useValue: {} }, { provide: PackagingService, useValue: serviceSpy }],
      declarations: [PackagingsComponent]
    })
      .compileComponents();

    serviceSpy.getTotalRecords.and.returnValue(of(2));
    serviceSpy.getPackagings.and.returnValue(of(expectedPackaging));

    fixture = TestBed.createComponent(PackagingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

    it('getPackagings should return expected packagings', () => {
        expect(component).toBeTruthy();
        expect(component.packagings).toBeDefined();
        expect(component.packagings.length).toBeGreaterThan(0);
        expect(component.packagings[0]).toEqual(expectedPackaging[0]);
    });
});
