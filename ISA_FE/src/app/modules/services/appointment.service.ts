import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  
  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  isAppointmentAvailable(appDTO: any): Observable<any>{
    return this.http.post<String>(this.apiHost + 'api/appointments/isAvailable', appDTO, {headers: this.headers})
  }

  saveAppointment(app: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/create', app, {headers: this.headers})
  }
}
