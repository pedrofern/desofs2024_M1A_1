import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { UserMap } from 'src/mappers/UserMap';
import { IUser } from 'src/model/IUser';
import { GlobalService } from 'src/services/global.service';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent {
    user: IUser | undefined;
    roles: IRoleDTO[] = [];
    success: boolean | undefined;
    errorMessage: string = "";
    successMessage: string = "";
    email: string = '';

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

    delete(form: NgForm){
      this.userService.deleteUser(
        form.value.email
      ).subscribe({
              error: (error: HttpErrorResponse) => {
                this.errorMessage = error.error.errors.message;
                this.success = false;
              },
              complete: () => {
                  this.success = true;
                  this.successMessage = 'The user was deleted!'
                  form.resetForm();
              },
          })
    }

}
