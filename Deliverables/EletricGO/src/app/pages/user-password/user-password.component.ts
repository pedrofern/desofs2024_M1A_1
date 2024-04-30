import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { IUser } from 'src/model/IUser';
import { GlobalService } from 'src/services/global.service';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-user-password',
  templateUrl: './user-password.component.html',
  styleUrls: ['./user-password.component.css']
})
export class UserPasswordComponent {

    role: string | undefined;
    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";
    email = '';
    password = '';

    constructor(private route: ActivatedRoute, private userService: UserService, private cookieService: CookieService, public global: GlobalService) {
    }

    ngOnInit(): void {
      this.global.updateData(true);
      if(this.cookieService.get('roleName') === 'Administrator'){
        this.role = '1';
      }else{
        this.role = '2';
        this.email = this.cookieService.get('email');
      }
    }

    edit(form: NgForm) {
      this.userService.editPassword(
          form.value.email,
          form.value.password
        ).subscribe({
                error: (error: HttpErrorResponse) => {
                  this.errorMessage = error.error.errors.message;
                  this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The user password was updated!'
                    form.resetForm();
                },
            })
    }

}
