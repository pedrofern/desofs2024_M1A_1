import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { ILogin } from 'src/dtos/login/ILogin';
import { LoginService } from 'src/services/login.service';
import { CookieService } from 'ngx-cookie-service';
import { IRole } from 'src/model/IRole';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { CredentialResponse, PromptMomentNotification } from 'google-one-tap';
import { EventAggregatorService } from 'src/services/event-aggregator.service';
import { EventArgs } from 'src/model/EventArgs';
import { GlobalService } from 'src/services/global.service';

@Component({
  selector: 'app-login',
  //standalone: true,
  //imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  private loginCredentials = {} as ILogin;
  public success: boolean | undefined;
  public successMessage = '';
  public errorMessage = '';
  public clientId = '145040217029-il1b9bhjda462rode1sh4fghmbg6omjl.apps.googleusercontent.com';

  constructor(private loginService: LoginService, private router: Router, private cookieService: CookieService, private eventService: EventAggregatorService, public global: GlobalService) { }

  // Client ID -> 145040217029-il1b9bhjda462rode1sh4fghmbg6omjl.apps.googleusercontent.com
  // Client secret -> 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

  ngOnInit(): void {
    this.eventService.LogoutRefresh.subscribe((e: EventArgs) => {
      // @ts-ignore
      window.onGoogleLibraryLoad();});
    // @ts-ignore
    window.onGoogleLibraryLoad = () => {this.googleButton();};
  }

  onRefresh(): void{
    this.googleButton();
  }

  googleButton(): void{
      // @ts-ignore
      google.accounts.id.initialize({
        client_id: this.clientId,
        callback: this.handleCredentialResponse.bind(this),
        auto_select: false,
        cancel_on_tap_outside: true
      });
      // @ts-ignore
      google.accounts.id.renderButton(
      // @ts-ignore
      document.getElementById("buttonDiv"),
        { theme: "outline", size: "large", width: "100%" } 
      );
      // @ts-ignore
      google.accounts.id.prompt((notification: PromptMomentNotification) => {});
  }

  validateLogin(form: NgForm) {

    if (form.value.email != '' && form.value.password != '') {

      this.loginCredentials.email = form.value.email;
      this.loginCredentials.password = form.value.password;

      this.loginService.validateLogin(this.loginCredentials)
        .subscribe((data: any) => {
            this.cookieService.set('email', data.userDTO.email);
            this.cookieService.set('token', data.token);
            this.cookieService.set('roleName', data.roleName);
            this.eventService.refreshSideBar();
            this.router.navigateByUrl('/');
        }, (error: HttpErrorResponse) => {
          this.errorMessage = error.error.errors.message;
          this.success = false;
        });
    } else {
      this.errorMessage = "Missing email or password";
      this.success = false;
    }
  }

  handleCredentialResponse(response: CredentialResponse) {
    this.loginService.validateLoginGoogle(response.credential)
        .subscribe((data: any) => {
            this.global.updateData(true);
            this.cookieService.set('email', data.email);
            this.cookieService.set('token', response.credential);
            this.cookieService.set('roleName', data.roleName);
            this.eventService.refreshSideBar();
            this.router.navigateByUrl('/');
        }, (error: HttpErrorResponse) => {
          this.errorMessage = error.error.errors.message;
          this.success = false;
        });
    }

}
