import { Component, Inject } from '@angular/core';
import { TwoFactorAuthService } from '../../../services/two-factor-auth-service.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-two-factor-auth',
  templateUrl: './two-factor-auth.component.html',
  styleUrls: ['./two-factor-auth.component.css']
})
export class TwoFactorAuthComponent {

  qrCodeUrl = "";
  secret = "";
  code = new FormControl('', [this.exactlySixDigits, this.onlyNumbers]);

  constructor(public dialogRef: MatDialogRef<TwoFactorAuthComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private twoFactorAuthService: TwoFactorAuthService) { }

  ngOnInit() {
    this.twoFactorAuthService.generate2FA(this.data.email).subscribe(response => {
      this.qrCodeUrl = response.qrCodeUrl;
      this.secret = response.secret; 
    });
  }

  validateCode() {
    this.dialogRef.close({code: this.code.value, secret: this.secret});
  }

  exactlySixDigits(control: AbstractControl) {
    const value = control.value;
    if (value && value.toString().length !== 6) {
      return { 'exactlySixDigits': true };
    }
    return null;
  }

  onlyNumbers(control: AbstractControl) {
    const value = control.value;
    if (value && !/^\d+$/.test(value)) {
      return { 'onlyNumbers': true };
    }
    return null;
  }

  numericOnly(event: KeyboardEvent): boolean {
    const char = event.key;
    if (!char.match(/[0-9]/)) {
      return false;
    }
    return true;
  }
}
