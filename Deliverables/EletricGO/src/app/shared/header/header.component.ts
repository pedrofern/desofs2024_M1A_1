import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { EventAggregatorService } from 'src/services/event-aggregator.service';
import { GlobalService } from 'src/services/global.service';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public flag: boolean | undefined;
  public successMessage = '';
  public errorMessage = '';

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private loginService: LoginService, private router: Router, private cookieService: CookieService, private eventService: EventAggregatorService, public global: GlobalService) { }

  ngOnInit(): void {
  }

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }

  public logout = () => {
    this.loginService.logout(this.cookieService.get('access_token'));
      this.cookieService.delete('email');
      this.cookieService.delete('access_token');
      this.cookieService.delete('role');
    this.global.updateData(false);
    this.eventService.refreshLogout();
    this.router.navigate(['/login']);
  }
}
