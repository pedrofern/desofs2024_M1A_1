import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import PageChangedEventArgs from './PageChangedEventArgs';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})

export class PaginatorComponent implements OnInit {
    @Input()
    currentPage: number = 1;

    @Input()
    itemsPerPage: number = 10;

    @Input()
    currentShownItems: number = 0;

    @Input()
    totalItems: number = 0;

    @Output() 
    PageChanged = new EventEmitter<PageChangedEventArgs>();
  
    constructor() { }

    ngOnInit(): void {
    }

    OnButtonClick(e: number) {
        switch(e) {
            case 1:
                if(this.currentPage <= 1) {
                    return;
                }
                this.currentPage -= 1;
                break;

            case 2:
                if(this.currentShownItems >= this.totalItems) {
                    return;
                }

                this.currentPage += 1;
                break;
        }

        this.PageChanged.emit({currentPage: this.currentPage, itemsPerPage: this.itemsPerPage});
    }

}
