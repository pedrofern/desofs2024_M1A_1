import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';
import { IWarehouseDto } from "../dtos/warehouse/IWarehouseDto";
import { IDeliveryDto } from "../dtos/delivery/IDeliveryDto";
import { IUpdateDeliveryDto } from "../dtos/delivery/IUpdateDeliveryDto";
import { ICreateDeliveryDto } from "../dtos/delivery/ICreateDeliveryDto";

@Injectable({ providedIn: 'root' })
export class DeliveryService {

  private deliveriesUrl = environment.APIDeliveries;
  private warehousesUrl = environment.APIWarehouses;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET deliveries from the server */
  getDeliveries(): Observable<IDeliveryDto[]> {
    return this.http.get<IDeliveryDto[]>(this.deliveriesUrl)
    .pipe(
      tap(_ => this.log('fetched deliveries')),
      catchError(this.handleError<IDeliveryDto[]>('getDeliveries', []))
    );
}

/** GET filtered deliveries from the server */
getFilterDeliveries(filters: any, sortBy: string, sortOrder: string, pageIndex: number, pageSize: number): Observable<IDeliveryDto[]> {
  let url = this.deliveriesUrl + 'Filter/';
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

  return this.http.get<IDeliveryDto[]>(url, { params })
    .pipe(
      tap(_ => this.log('fetched deliveries')),
      catchError(this.handleError<IDeliveryDto[]>('getDeliveries', []))
    );
}

/** GET total deliveries from the server */
getTotalRecords(): Observable<IDeliveryDto[]> {
  return this.http.get<IDeliveryDto[]>(this.deliveriesUrl)
      .pipe(
        tap(_ => this.log('fetched deliveries')),
        catchError(this.handleError<IDeliveryDto[]>('getDeliveries', []))
      );
  }

  /** GET warehouses from the server */
  getWarehouses(): Observable<IWarehouseDto[]> {
    const url = `${this.warehousesUrl}active`;
    return this.http.get<IWarehouseDto[]>(url)
      .pipe(
        tap(_ => this.log('fetched warehouses')),
        catchError(this.handleError<IWarehouseDto[]>('getWarehouses', []))
      );
  }

  /** GET: Get a Delivery from server */
  getDelivery(id: number): Observable<IDeliveryDto> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get<IDeliveryDto>(this.deliveriesUrl + id, { headers });
  }

  //////// Save methods //////////
  /** POST: add a new Delivery to the server */
  addDelivery(request: ICreateDeliveryDto): Observable<ICreateDeliveryDto> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<ICreateDeliveryDto>(this.deliveriesUrl, request, { headers });
  }

  /** PUT: edit a packaging to the server */
  editDelivery(id: string, requestRes: IUpdateDeliveryDto): Observable<IUpdateDeliveryDto> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<IUpdateDeliveryDto>(this.deliveriesUrl + id, requestRes, { headers });
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

  /** Log a DeliveryService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`DeliveryService: ${message}`);
  }
}
