import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodSubscriptionDTO } from '../model/bloodSubscription.model';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class BloodSubsctiprionServiceService {

  apiHost: string = 'http://localhost:8081/';
  // headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });
  private createHeaders(): HttpHeaders {
    const token = this.authService.getAuthToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAllForLoggedAdministrator(): Observable<BloodSubscriptionDTO[]> {
    return this.http.get<BloodSubscriptionDTO[]>(this.apiHost + 'api/bloodSubscription', {headers: this.createHeaders()});
  }
}
