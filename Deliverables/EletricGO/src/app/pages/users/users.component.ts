import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subject, takeUntil } from 'rxjs';
import { IUser } from 'src/model/IUser';
import { UserService } from 'src/services/user.service';
import {UserMap} from 'src/mappers/UserMap';
import { CookieService } from 'ngx-cookie-service';
import { GlobalService } from 'src/services/global.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, OnDestroy {

  users: IUser[] = [];
  role: string | undefined;
  displayedColumns: string[] = ['userName', 'firstName', 'lastName', 'phoneNumber', 'email', 'role', 'actions'];
  dataSource!: MatTableDataSource<IUser>;
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
      private userService: UserService,
      public dialog: MatDialog,
      private cookieService: CookieService,
      public global: GlobalService
  ) {
  }

  @ViewChild(MatSort) sort: MatSort = new MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
    this.global.updateData(true);
    if(this.cookieService.get('role') === 'ADMIN'){
      this.role = '1';
    }
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => {
          this.users = UserMap.toModelList(data);
          this.dataSource = new MatTableDataSource(this.users);
          this.dataSource.sort = this.sort;
          setTimeout(() => this.dataSource.paginator = this.paginator);
      })
  }

  ngOnDestroy() {
      this.destroy$.next(true);
      this.destroy$.unsubscribe();
  }

  applyFilter(event: any) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filterValue.trim().toLocaleLowerCase();
  }
}
