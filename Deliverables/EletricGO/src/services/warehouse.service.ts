import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {MessageService} from './message.service';
import {ICreateWarehouseDto} from "../dtos/warehouse/ICreateWarehouseDto";
import {IWarehouseDto} from "../dtos/warehouse/IWarehouseDto";
import {IUpdateWarehouseDto} from "../dtos/warehouse/IUpdateWarehouseDto";


@Injectable({ providedIn: 'root' })
export class WarehouseService {

  private warehousesUrl = environment.APIWarehouses;  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET warehouses from the server */
  getWarehouses(): Observable<IWarehouseDto[]> {
    return this.http.get<IWarehouseDto[]>(environment.APIWarehouses)
      .pipe(
        tap(_ => this.log('fetched warehouses')),
        catchError(this.handleError<IWarehouseDto[]>('getWarehouses', []))
      );
  }

  /** GET active warehouses from the server */
  getActiveWarehouses(): Observable<IWarehouseDto[]> {
  const url = `${this.warehousesUrl}active`;
    return this.http.get<IWarehouseDto[]>(url)
      .pipe(
        tap(_ => this.log('fetched active warehouses')),
          catchError(this.handleError<IWarehouseDto[]>('active warehouses', []))
      );
  }
  
  /** GET filtered warehouses from the server */
  getFilterWarehouses(filters: any, sortBy: string, sortOrder: string, pageIndex: number, pageSize: number): Observable<IWarehouseDto[]> {
    let url = `${this.warehousesUrl}filter`;
    let params = new HttpParams();
  
    // Add the filters to the params object
    Object.keys(filters).forEach(key => {
      if (filters[key]) {
        params = params.append(key, filters[key]);
      }
    });
  
    // Add the sort options to the params object
    if (sortBy && sortOrder) {
      params = params.append('sortBy', sortBy);
      params = params.append('sortOrder', sortOrder.toUpperCase());
    } else {
      params = params.append('sortBy', 'Id');
      params = params.append('sortOrder', 'ASC');
    }
  
    // Add the pagination options to the params object
    params = params.append('pageIndex', pageIndex);
    params = params.append('pageSize', pageSize);
  
    return this.http.get<IWarehouseDto[]>(url, { params })
      .pipe(
        tap(_ => this.log('fetched warehouses')),
        catchError(this.handleError<IWarehouseDto[]>('getwarehouses', []))
      );
  }

  /** GET: Get a Delivery from server */
  getWarehouse(identifier: string): Observable<IWarehouseDto> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get<IWarehouseDto>(this.warehousesUrl + identifier, { headers });
  }

  //////// Save methods //////////
  /** POST: add a new Delivery to the server */
  addWarehouse(request: ICreateWarehouseDto): Observable<ICreateWarehouseDto> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<ICreateWarehouseDto>(this.warehousesUrl, request, { headers });
  }

  /** PUT: edit a packaging to the server */
  editWarehouse(id: string, requestRes: IUpdateWarehouseDto): Observable<IUpdateWarehouseDto> {
    const headers = {'Content-Type': 'application/json'};
    return this.http.put<IUpdateWarehouseDto>(this.warehousesUrl + id, requestRes, {headers});
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }
}
