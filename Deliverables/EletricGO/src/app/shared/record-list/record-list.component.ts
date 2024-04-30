import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {GeneralItemDTO} from '../../../dtos/shared/generalItemDto';

@Component({
  selector: 'app-record-list',
  templateUrl: './record-list.component.html',
  styleUrls: ['./record-list.component.css']
})
export class RecordListComponent implements OnInit {

  @Input() records: GeneralItemDTO[] = [];

  @Output() recordClicked = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {

  }

  onRecordClicked(id: string) {
    this.recordClicked.emit(id);
  }

}
