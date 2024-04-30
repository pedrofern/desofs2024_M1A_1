import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import ITruckDTO from 'src/dtos/truck/ITruckDTO';
import IUpdateTruckDTO from 'src/dtos/truck/IUpdateTruckDTO';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class TruckService {

  private trucksUrl = environment.APITrucks;

  httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }
  
  /** POST: add a new truck to the server */
  addTruck(request: ITruckDTO): Observable<ITruckDTO> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<ITruckDTO>(this.trucksUrl, request, { headers });
  }

  /** PUT: update a truck on the server */
  updateTruck(truckId: string, truck: IUpdateTruckDTO): Observable<IUpdateTruckDTO> {
    return this.http.put<IUpdateTruckDTO>(this.trucksUrl + truckId, truck, this.httpOptions).pipe(
      tap(_ => this.log(`updated truck id=${truckId}`)),
      catchError(this.handleError<IUpdateTruckDTO>('updateTruck'))
      );
    }

  /** GET trucks from the server */
  getTrucks(filters: any, sortBy: string, sortOrder: string, pageIndex: number, pageSize: number): Observable<ITruckDTO[]> {

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
    } else {
      params = params.append('sortBy', 'Id');
      params = params.append('sortOrder', 'ASC');
    }

    // Add the pagination options to the params object
    params = params.append('skip', (pageIndex * pageSize));
    params = params.append('limit', pageSize);

    return this.http.get<ITruckDTO[]>(this.trucksUrl, { params })
      .pipe(
        tap(_ => this.log('fetched trucks')),
        catchError(this.handleError<ITruckDTO[]>('getTrucks', []))
      );
    }
  
    /** GET active trucks from the server */
    getActiveTrucks(): Observable<ITruckDTO[]> {
      const url = `${this.trucksUrl}active`;
      return this.http.get<ITruckDTO[]>(url)
        .pipe(
          tap(_ => this.log('fetched active trucks')),
          catchError(this.handleError<ITruckDTO[]>('active trucks', []))
        );
    } 

  /** GET truck by id. Return `undefined` when id not found */
  getTruckNo404<Data>(truckId: string): Observable<ITruckDTO> {
    const url = `${this.trucksUrl}/?id=${truckId}`;
    return this.http.get<ITruckDTO[]>(url)
    .pipe(
      map(trucks => trucks[0]), // returns a {0|1} element array
      tap(t => {
        const outcome = t ? 'fetched' : 'did not find';
        this.log(`${outcome} truck id=${truckId}`);
      }),
      catchError(this.handleError<ITruckDTO>(`getTruck id=${truckId}`))
      );
    }

  /** GET truck by id. Will 404 if id not found */
  getTruck(truckId: string): Observable<ITruckDTO> {
    const url = `${this.trucksUrl}/${truckId}`;
      return this.http.get<ITruckDTO>(url)
      .pipe(
        tap(_ => this.log(`fetched truck id=${truckId}`)),
        catchError(this.handleError<ITruckDTO>(`getTruck id=${truckId}`))
        );
      }

      getTotalRecords(): Observable<number> {
        const url = `${this.trucksUrl}total/get`;
        return this.http.get<number>(url)
          .pipe(
            tap(() => this.log(`fetched count trucks`)),
            catchError(this.handleError<number>(`trucks count`))
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

      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`TruckService: ${message}`);
  }
}

