import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TokenService } from 'src/services/TokenService';
import { EventArgs } from 'src/model/EventArgs';
import { EventAggregatorService } from 'src/services/event-aggregator.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  @Output() sidenavClose = new EventEmitter();

  title = "ElectricGo";
  role: string | undefined;

  constructor(private tokenService: TokenService, private eventService: EventAggregatorService) { }

  ngOnInit(): void {
    this.eventService.SideBarRefresh.subscribe((e: EventArgs) => this.onRefresh());
    this.validateRole();
  }

  public onSidenavClose = () => {
    this.sidenavClose.emit();
  }

  validateRole(): void {

  const roleFromToken = this.tokenService.getRole();
    if(roleFromToken === 'ADMIN'){
      this.role = '1';
    } else if (roleFromToken === 'WAREHOUSE_MANAGER'){
      this.role = '2';
    } else if (roleFromToken === 'FLEET_MANAGER'){
      this.role = '3';
    } else if (roleFromToken === 'LOGISTICS_MANAGER'){
        this.role = '4';
    } else if (roleFromToken === 'OPERATOR'){
        this.role = '5';
    }
  }

  onRefresh(): void{
    this.validateRole();
  }
}
