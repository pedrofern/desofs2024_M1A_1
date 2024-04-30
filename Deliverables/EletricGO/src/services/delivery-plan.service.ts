import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { ICreateDeliveryPlanDTO } from 'src/dtos/deliveryplan/ICreateDeliveryPlanDTO';
import { IDeliveryPlanDTO } from 'src/dtos/deliveryplan/IDeliveryPlanDTO';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class DeliveryPlanService {

  private createdeliveryplansUrl = environment.APICreatePlannings;  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET deliveryplans from the server */
  getDeliveryPlans(filters: any, sortBy: string, sortOrder: string, pageIndex: number, pageSize: number): Observable<IDeliveryPlanDTO[]> {

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
      params = params.append('sortOrder', sortOrder);
    }

    // Add the pagination options to the params object
    params = params.append('skip', (pageIndex * pageSize));
    params = params.append('limit', pageSize);

    return this.http.get<IDeliveryPlanDTO[]>(environment.APIPlannings, {params})
      .pipe(
        tap(_ => this.log('fetched deliveries')),
        catchError(this.handleError<IDeliveryPlanDTO[]>('getDeliveryPlans', []))
      );
  }

  //////// Save methods //////////
  /** POST: add a new Delivery to the server */
  addDeliveryPlan(request: ICreateDeliveryPlanDTO) {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get(`${this.createdeliveryplansUrl}?datedelivery=${request.date}&truckId=${request.truckId}&algorithm=${request.algorithm}`);
  }

  getTotalRecords(): Observable<number> {
    const url = `${environment.APIPlannings}total/get`;
    return this.http.get<number>(url)
      .pipe(
        tap(() => this.log(`fetched count delivery plans`)),
        catchError(this.handleError<number>(`delivery plans count`))
      );
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
