import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { IEmailDTO } from 'src/dtos/login/IEmailDTO';
import { ILogin } from 'src/dtos/login/ILogin';
import IRoleDTO from 'src/dtos/login/IRoleDTO';
import { environment } from 'src/environments/environment';
import { IRole } from 'src/model/IRole';
import { IUser } from 'src/model/IUser';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = environment.APIUsers;  // URL to web api

  private subjectName = new Subject<any>(); //need to create a subject

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient,
    private messageService: MessageService) { }

  validateLogin(login: ILogin): Observable<IUser> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<IUser>(this.loginUrl + '/login', login, { headers });
  }

  validateLoginGoogle(token: string): Observable<IUser> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.get<IUser>(this.loginUrl + '/loginGoogle/' + token, { headers });
  }

  validateIsLoggedIn(token: string): any {
    const headers = { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` };
    this.http.post<HttpResponse<any>>(this.loginUrl + '/isLoggedIn', { headers })
                        .subscribe(response => {
                          if(!response.ok){
                            return false;
                          }
                          return true;
                        });
  }

  logout(token: string): any {
    const headers = { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` };
    this.http.post<HttpResponse<any>>(this.loginUrl + '/logout', { headers })
                  .subscribe(response => {
                    if(!response.ok){
                      return false;
                    }
                    return true;
                  });
  }

  getRoleByUser(roleId: string): Observable<IRoleDTO> {
    const headers = { 'Content-Type': 'application/json'};
    return this.http.get<IRoleDTO>(this.loginUrl + '/roleByUser/' + roleId, { headers });
  }
}
