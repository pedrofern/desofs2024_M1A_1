import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { jwtDecode, JwtPayload } from 'jwt-decode';

interface MyTokenPayload extends JwtPayload {
    roles: { authority: string }[];
  }

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private cookieService: CookieService) { }

  private decodeAccessToken() {
    const token = this.cookieService.get('access_token');
    if (!token) {
        return null;
    }

    try {
        const decoded = jwtDecode(token);
        return decoded;
    } catch (error) {
        console.error('Error decoding token', error);
        return null;
    }
  }

    public getRole(): string | undefined {
        const decodedToken = this.decodeAccessToken() as MyTokenPayload;
        if (!decodedToken) {
            return undefined;
        }
    
        const roleFromToken = decodedToken.roles[0].authority;
        if(roleFromToken === 'ADMIN'){
        return '1';
        } else if (roleFromToken === 'WAREHOUSE_MANAGER'){
        return '2';
        } else if (roleFromToken === 'FLEET_MANAGER'){
        return '3';
        } else if (roleFromToken === 'LOGISTICS_MANAGER'){
        return '4';
        } else if (roleFromToken === 'OPERATOR'){
        return '5';
        }
        return undefined;
    }

    public getEmail(): string | undefined {
        const decodedToken = this.decodeAccessToken();
        if (!decodedToken) {
            return undefined;
        }

        return decodedToken.sub;
    }

}