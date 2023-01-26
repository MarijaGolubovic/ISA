import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentResponse, AppointmentUserDTO, FutureAppointmentDTO } from '../model/appointment.model';
import { AppointmentUserDTO, FutureAppointmentBBDTO, FutureAppointmentDTO } from '../model/appointment.model';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  
  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  isAppointmentAvailable(appDTO: any): Observable<any>{
    return this.http.post<String>(this.apiHost + 'api/appointments/isAvailable', appDTO, {headers: this.headers})
  }

  saveAppointment(app: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/create', app, {headers: this.headers})
  }

  scheduleAppointment(app: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/schedule', app, {headers: this.headers})
  }

  getFutureAppointments(): Observable<FutureAppointmentDTO[]> {
    return this.http.get<FutureAppointmentDTO[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentsForLoggedUser', {headers: this.headers});
  }

  getFutureAppointmentByID(): Observable<FutureAppointmentDTO[]> {
    return this.http.get<FutureAppointmentDTO[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentsForLoggedUser', {headers: this.headers});
  }

  getAppointmentByID(id : number): Observable<AppointmentUserDTO> {
    return this.http.get<AppointmentUserDTO>(this.apiHost + 'api/appointments/getAppointment/' + id.toString(), {headers: this.headers});
  }

  updateAppointment(app: any, id:number): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/addSurvey/'+ id.toString(), app, {headers: this.headers})
  }

  getApointmetnResponsesForCalendar(): Observable<any[]> {
    return this.http.get<AppointmentResponse[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentResponsesForLoggedUser', {headers: this.headers});
  }
  getFutureAppointmentsBB(): Observable<FutureAppointmentBBDTO[]> {
    return this.http.get<FutureAppointmentBBDTO[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentsForBB/' + '2', {headers: this.headers});
  }
}
