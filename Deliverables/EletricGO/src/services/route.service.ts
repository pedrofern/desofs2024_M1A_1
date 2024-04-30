import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, Observable, of, tap } from 'rxjs';

import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';

import { IRoute } from 'src/model/routes/IRoute';

import { RouteDTO } from 'src/dtos/routes/routeDto';
import { ICreateRouteDTO } from "src/dtos/routes/ICreateRouteDTO";
import { IUpdateRouteDTO } from "src/dtos/routes/IUpdateRouteDTO";
import { RouteMap } from 'src/mappers/RouteMap';

@Injectable({ providedIn: 'root' })
export class RouteService {

  private requestUrl = environment.APIRoutes;

  httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getRoutes(filters: any, sortBy: string, sortOrder: string, pageIndex: number, pageSize: number): Observable<RouteDTO[]> {
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

    return this.http.get<RouteDTO[]>(this.requestUrl, { params });
  }

  getRoute(id: string): Observable<RouteDTO> {
    return this.http.get<RouteDTO>(`${this.requestUrl}/${id}`);
  }

  getTotalRecords(): Observable<number> {
    return this.http.get<number>(`${this.requestUrl}/total/get`);
  }

  createRoute(route: IRoute) {
    const data = RouteMap.toCreationDTO(route);
    return this.http.post<ICreateRouteDTO>(this.requestUrl, data, this.httpOptions);
  }

  updateRoute(id: string, route: IRoute) {
    const data = RouteMap.toUpdateDTO(route);
    return this.http.put<ICreateRouteDTO>(`${this.requestUrl}/${id}`, data, this.httpOptions);
  }

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

  private log(message: string) {
    this.messageService.add(`PackagingService: ${message}`);
  }
}