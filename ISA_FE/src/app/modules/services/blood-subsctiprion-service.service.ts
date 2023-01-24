import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodSubscriptionDTO } from '../model/bloodSubscription.model';

@Injectable({
  providedIn: 'root'
})
export class BloodSubsctiprionServiceService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  getAllForLoggedAdministrator(): Observable<BloodSubscriptionDTO[]> {
    return this.http.get<BloodSubscriptionDTO[]>(this.apiHost + 'api/bloodSubscription', {headers: this.headers});
  }
}
