import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/services/global.service';

@Component({
    selector: 'app-homepage',
    templateUrl: './homepage.component.html',
    styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

    constructor(public global: GlobalService) { }

    ngOnInit(): void {
        this.global.updateData(true);
    }
}