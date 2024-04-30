import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/services/global.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(public global: GlobalService) {
  }

  ngOnInit(): void {
    this.updateData();
  }

  async updateData() {
    this.global.updateData(false);
  }

  validateLogin() {
    return this.global.isLoggedIn;
  }

}