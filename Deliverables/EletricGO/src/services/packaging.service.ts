import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

import {environment} from 'src/environments/environment';
import {ICreatePackagingDTO} from 'src/dtos/packaging/ICreatePackagingDTO';
import {IUpdatePackagingDTO} from 'src/dtos/packaging/IUpdatePackagingDTO';
import {IPackagingDTO} from 'src/dtos/packaging/IPackagingDTO';
import {IPackagingCreate} from 'src/model/IPackagingCreate';
import {IPackagingUpdate} from 'src/model/IPackagingUpdate';
import {catchError, Observable, of, tap} from 'rxjs';
import {MessageService} from './message.service';

@Injectable({
    providedIn: 'root'
})
export class PackagingService {

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    private packaginsRequestUrl = environment.APIPackagings;

    constructor(
        private http: HttpClient,
        private messageService: MessageService
    ) {
    }

    /** POST: add a new packaging to the server */
    createPackaging(request: IPackagingCreate) {
        const headers = {'Content-Type': 'application/json'};
        return this.http.post<ICreatePackagingDTO>(this.packaginsRequestUrl, request, {headers});
    }

    /** PUT: edit a packaging to the server */
    editPackaging(id: string, requestRes: IPackagingUpdate) {
        const headers = {'Content-Type': 'application/json'};
        return this.http.put<IUpdatePackagingDTO>(this.packaginsRequestUrl + id, requestRes, {headers});
    }

    /** GET packagings from the server */
    getPackagings(filters: any, sortBy: string, sortOrder: string, pageIndex: number, pageSize: number): Observable<IPackagingDTO[]> {
        const url = this.packaginsRequestUrl + 'filter';
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

        return this.http.get<IPackagingDTO[]>(url, {params})
            .pipe(
                tap(_ => this.log('fetched packagings')),
                catchError(this.handleError<IPackagingDTO[]>('getPackagings', []))
            );
    }

    /** GET packaging by id. Will 404 if id not found */
    getPackaging(id: string): Observable<IPackagingDTO> {
        const url = `${this.packaginsRequestUrl}/${id}`;
        return this.http.get<IPackagingDTO>(url)
            .pipe(
                tap(_ => this.log(`fetched packaging id=${id}`)),
                catchError(this.handleError<IPackagingDTO>(`getPackaging id=${id}`))
            );
    }

    getTotalRecords(): Observable<number> {
        const url = `${this.packaginsRequestUrl}total/get`;
        return this.http.get<number>(url)
            .pipe(
                tap(() => this.log(`fetched count packagings`)),
                catchError(this.handleError<number>(`packagings count`))
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
        this.messageService.add(`PackagingService: ${message}`);
    }
}
