import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, of } from 'rxjs';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { IUserDTO } from 'src/dtos/user/IUserDTO';
import { ICreateUserDTO } from 'src/dtos/user/ICreateUserDTO';
import { environment } from 'src/environments/environment';
import { MessageService } from './message.service';
import { IUpdateUserDTO } from 'src/dtos/user/IUpdateUserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = environment.APIUsers;  // URL to web api
  private usersPasswordUrl = environment.APIPassword;  // URL to web api
  private deleteUserUrl = environment.APIDeleteUser;  // URL to web api
  private loginUrl = environment.APILogin;  // URL to web api
  private rolesUrl = environment.APIRoles;  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET Users from the server */
  getUsers(): Observable<IUserDTO[]> {
    return this.http.get<IUserDTO[]>(this.usersUrl)
      .pipe(
        tap(_ => this.log('fetched users')),
        catchError(this.handleError<IUserDTO[]>('getUsers', []))
      );
  }

  /** GET: Get a User from server */
  getUser(email: string): Observable<IUserDTO> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get<IUserDTO>(this.usersUrl + email, { headers });
  }

  getUserByEmail(email: string): Observable<IUserDTO[]> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get<IUserDTO[]>(this.usersUrl + email, { headers });
  }

  /** GET Users from the server */
  getRoles(): Observable<IRoleDTO[]> {
    return this.http.get<IRoleDTO[]>(this.rolesUrl)
      .pipe(
        tap(_ => this.log('fetched roles')),
        catchError(this.handleError<IRoleDTO[]>('getRoles', []))
      );
  }

  //////// Save methods //////////
  /** POST: add a new User to the server */
  addUser(request: ICreateUserDTO): Observable<ICreateUserDTO> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<ICreateUserDTO>(this.loginUrl + 'signup', request, { headers });
  }

  /** PUT: edit a user to the server */
  editUser(email: string, requestRes: IUpdateUserDTO): Observable<IUpdateUserDTO> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<IUpdateUserDTO>(this.usersUrl + email, requestRes, { headers });
  }

  /** PUT: edit a user to the server */
  editPassword(email: string, password: string): Observable<IUpdateUserDTO> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<IUpdateUserDTO>(this.usersPasswordUrl + email, password, { headers });
  }

  /** PUT: delete a user to the server */
  deleteUser(email: string): Observable<IUserDTO> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<IUserDTO>(this.deleteUserUrl + email, { headers });
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
