import { EventEmitter, Injectable } from '@angular/core';
import { EventArgs } from 'src/model/EventArgs';

@Injectable({
  providedIn: 'root'
})
export class EventAggregatorService {

  constructor() { }

  SideBarRefresh = new EventEmitter<EventArgs>();
  LogoutRefresh = new EventEmitter<EventArgs>();

  public refreshSideBar(): void{
    this.SideBarRefresh.emit({});
  }

  public refreshLogout(): void{
    this.LogoutRefresh.emit({});
  }
}
