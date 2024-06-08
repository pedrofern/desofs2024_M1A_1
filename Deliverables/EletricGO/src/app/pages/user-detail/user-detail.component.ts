import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRoleDTO } from 'src/dtos/role/IRoleDTO';
import { IUser } from 'src/model/IUser';
import { UserService } from 'src/services/user.service';
import { UserMap } from 'src/mappers/UserMap';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { GlobalService } from 'src/services/global.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

    user: IUser | undefined;
    roles: IRoleDTO[] = [];
    success: boolean | undefined;
    errorMessage = "";
    successMessage = "";
    email = '';

    constructor(private route: ActivatedRoute, private userService: UserService, public global: GlobalService) {
    }

    ngOnInit(): void {
        this.global.updateData(true);
        this.getUser();
        this.getRoles();
    }

    getUser(): void {
      this.email = this.route.snapshot.paramMap.get('email')!;
      if (this.email) {
          this.userService.getUser(this.email)
              .subscribe(data => this.user = UserMap.toModel(data));
      } else {
          this.user = {} as IUser;
      }
    }

    getRoles(): void {
        this.roles = [
            { id: '1', name: 'ADMIN' },
            { id: '2', name: 'WAREHOUSE_MANAGER' },
            { id: '3', name: 'FLEET_MANAGER' },
            { id: '4', name: 'LOGISTICS_MANAGER' },
            { id: '5', name: 'OPERATOR' }
        ];
    }

    edit(form: NgForm) {
        this.userService.editUser(
            this.email,
            {            
            role: String(form.value.role)
          })
              .subscribe({
                  error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.error.errors.message;
                    this.success = false;
                  },
                  complete: () => {
                      this.success = true;
                      this.successMessage = 'The user was updated!'
                      form.resetForm();
                      setTimeout(() => {
                        window.history.back();
                    }, 1500);
                  },
              })
    }

    create(form: NgForm) {
        this.userService.addUser({
            userName: form.value.username,
            firstName: form.value.firstname,
            lastName: form.value.lastname,
            phoneNumber: form.value.phonenumber,
            email: form.value.email,
            password: form.value.password,
            role: String(form.value.role)
        })
            .subscribe({
                error: (error: HttpErrorResponse) => {
                    this.errorMessage = error.name + ' ' + error.status;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The user was created!'
                    form.resetForm();
                    setTimeout(() => {
                        window.history.back();
                    }, 1500);
                },
            })
    }

}
