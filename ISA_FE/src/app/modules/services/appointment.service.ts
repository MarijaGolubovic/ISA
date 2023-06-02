import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentResponse, AppointmentUserDTO, FutureAppointmentBBDTO, FutureAppointmentDTO } from '../model/appointment.model';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  
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

  isAppointmentAvailable(appDTO: any): Observable<any>{
    return this.http.post<String>(this.apiHost + 'api/appointments/isAvailable', appDTO, {headers: this.createHeaders()})
  }

  saveAppointment(app: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/create', app, {headers: this.createHeaders()})
  }

  scheduleAppointment(app: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/schedule', app, {headers: this.createHeaders()})
  }

  getFutureAppointments(): Observable<FutureAppointmentDTO[]> {
    return this.http.get<FutureAppointmentDTO[]>(this.apiHost + 'api/appointments/getBusyApointmentForUser', {headers: this.createHeaders()});
  }

  getFutureAppointmentByID(): Observable<FutureAppointmentDTO[]> {
    return this.http.get<FutureAppointmentDTO[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentsForLoggedUser', {headers: this.createHeaders()});
  }

  getAppointmentByID(id : number): Observable<AppointmentUserDTO> {
    return this.http.get<AppointmentUserDTO>(this.apiHost + 'api/appointments/getAppointment/' + id.toString(), {headers: this.createHeaders()});
  }

  updateAppointment(app: any, id:number): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/addSurvey/'+ id.toString(), app, {headers: this.createHeaders()})
  }

  getApointmetnResponsesForCalendar(): Observable<any[]> {
    return this.http.get<AppointmentResponse[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentResponsesForLoggedUser', {headers: this.createHeaders()});
  }
  getFutureAppointmentsBB(): Observable<FutureAppointmentBBDTO[]> {
    return this.http.get<FutureAppointmentBBDTO[]>(this.apiHost + 'api/appointments/getAllFutureAppointmentsForBB/' + '2', {headers: this.createHeaders()});
  }

  getAllFreeAppointments(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiHost}api/appointments/getAllFree`, {headers: this.createHeaders()});
  }

  takeAppointment(appointmentId: number): Observable<any> {
    return this.http.post(`${this.apiHost}api/appointments/takeAppointment/${appointmentId}`, null, {headers: this.createHeaders()});
  }

  getAppointmentHistoryForUser(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiHost}api/appointments/getHistoryForUser`, {headers: this.createHeaders()});
  }

  cancelAppointment(appointmentId: number): Observable<any> {
    return this.http.post(`${this.apiHost}api/appointments/cancelAppointment/${appointmentId}`, null, {headers: this.createHeaders()});
  }

  loadQRCodes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiHost}api/appointments/loadQRCodes`, {headers: this.createHeaders()});
  }

  getPenalsNumber(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiHost}api/appointments/getPenalNumber`, {headers: this.createHeaders()});
  }
}
