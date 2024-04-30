import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IRoleDTO } from 'src/dtos/role/IRoleDTO';
import { IRole } from 'src/model/IRole';
import { IUserDTO } from 'src/dtos/user/IUserDTO';
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
        this.userService.getRoles()
            .subscribe((data: IRoleDTO[]) => {
                this.roles = data
            });
    }

    edit(form: NgForm) {
        this.userService.editUser(
            this.email,
            {
            userName: form.value.username,
            firstName: form.value.firstname,
            lastName: form.value.lastname,
            phoneNumber: form.value.phonenumber,
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
                    this.errorMessage = error.error.errors.message;
                    this.success = false;
                },
                complete: () => {
                    this.success = true;
                    this.successMessage = 'The user was created!'
                    form.resetForm();
                },
            })
    }

}
