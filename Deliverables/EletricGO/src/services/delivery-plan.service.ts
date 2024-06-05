import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, of, tap} from 'rxjs';
import {environment} from 'src/environments/environment';
import {MessageService} from './message.service';
import {IDeliveryDto} from "../dtos/delivery/IDeliveryDto";
import {IWarehouseDto} from "../dtos/warehouse/IWarehouseDto";

@Injectable({
    providedIn: 'root'
})
export class DeliveryPlanService {

    private deliveriesUrl = environment.APIDeliveries;
    private warehousesUrl = environment.APIWarehouses;

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    constructor(
        private http: HttpClient,
        private messageService: MessageService) {
    }

    /** GET deliveries from the server */
    getDeliveries(): Observable<IDeliveryDto[]> {
        return this.http.get<IDeliveryDto[]>(this.deliveriesUrl)
            .pipe(
                tap(_ => this.log('fetched deliveries')),
                catchError(this.handleError<IDeliveryDto[]>('getDeliveries', []))
            );
    }

    /** GET warehouses from the server */
    getWarehouses(): Observable<IWarehouseDto[]> {
        const url = `${this.warehousesUrl}`;
        return this.http.get<IWarehouseDto[]>(url)
            .pipe(
                tap(_ => this.log('fetched warehouses')),
                catchError(this.handleError<IWarehouseDto[]>('getWarehouses', []))
            );
    }

    getDeliveryPlan(deliveryId: number, warehouseId: number): Observable<any> {
        const params = new HttpParams()
            .set('deliveryId', deliveryId)
            .set('warehouseId', warehouseId);
        const url = `${this.deliveriesUrl}delivery-plan/pdf`;

        return this.http.get(url, {params, responseType: 'arraybuffer'})
            .pipe(
                tap(_ => this.log('fetching pdf')),
                catchError(this.handleError<IWarehouseDto[]>('getDeliveryPlan', []))
            );
    }

    downloadPdf(deliveryId: number, warehouseId: number): Observable<any> {
        const params = new HttpParams()
            .set('deliveryId', deliveryId)
            .set('warehouseId', warehouseId);
        const url = `${this.deliveriesUrl}delivery-plan/pdf`;

        return this.http.get(url, {params, responseType: 'arraybuffer'})
            .pipe(
                tap(_ => this.log('download pdf')),
                catchError(this.handleError<IWarehouseDto[]>('downloadPdf', []))
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
