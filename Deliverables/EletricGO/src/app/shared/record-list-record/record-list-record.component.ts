import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
    selector: 'app-record-list-record',
    templateUrl: './record-list-record.component.html',
    styleUrls: ['./record-list-record.component.css']
})

export class RecordListRecordComponent implements OnInit {
    @Input() id = "";
    @Input() description = "";

    @Output() recordClicked = new EventEmitter<string>();

    constructor() { }

    ngOnInit(): void {
    }

    getId(): string {
        return this.id;
    }

    getDescription(): string {
        return this.description;
    }

    onClick(e: MouseEvent) {
        this.recordClicked.emit(this.id);
    }
}
