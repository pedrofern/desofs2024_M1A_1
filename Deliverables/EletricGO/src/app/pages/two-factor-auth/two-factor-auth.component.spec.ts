import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TwoFactorAuthComponent } from './two-factor-auth.component';

describe('TwoFactorAuthComponent', () => {
  let component: TwoFactorAuthComponent;
  let fixture: ComponentFixture<TwoFactorAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ TwoFactorAuthComponent ],
      providers: [
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: {} }
        // other providers...
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TwoFactorAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
