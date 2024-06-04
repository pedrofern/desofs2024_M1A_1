import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { EventArgs } from 'src/model/EventArgs';
import { EventAggregatorService } from 'src/services/event-aggregator.service';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  @Output() sidenavClose = new EventEmitter();

  title = "ElectricGo";
  role: string | undefined;

  constructor(private cookieService: CookieService, private eventService: EventAggregatorService) { }

  ngOnInit(): void {
    this.eventService.SideBarRefresh.subscribe((e: EventArgs) => this.onRefresh());
    this.validateRole();
  }

  public onSidenavClose = () => {
    this.sidenavClose.emit();
  }

  validateRole(): void {
    if(this.cookieService.get('role') === 'ADMIN'){
      this.role = '1';
    } else if (this.cookieService.get('role') === 'WAREHOUSE_MANAGER'){
      this.role = '2';
    } else if (this.cookieService.get('role') === 'FLEET_MANAGER'){
      this.role = '3';
    } else if (this.cookieService.get('role') === 'LOGISTICS_MANAGER'){
        this.role = '4';
    } else if (this.cookieService.get('role') === 'OPERATOR'){
        this.role = '5';
    }
  }

  onRefresh(): void{
    this.validateRole();
  }
}
