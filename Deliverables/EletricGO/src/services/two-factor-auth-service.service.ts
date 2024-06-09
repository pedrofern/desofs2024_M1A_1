import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TwoFactorAuthService {

  private apiUrl = environment.APIUsers;

  constructor(private http: HttpClient) { }

  generate2FA(username: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/login/generatef2a/${username}`);
  }
}
