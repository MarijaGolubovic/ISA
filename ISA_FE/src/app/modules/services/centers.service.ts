import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankResponse } from '../model/bloodBank.model';
import { BloodSupply } from '../model/bloodSupply.model';
import {CenterResponse } from '../model/center.model';
import { WholeUserResponse } from '../model/user.model';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

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

  public getCenterResponses(): Observable<CenterResponse[]> {
    return this.http.get<CenterResponse[]>(this.apiHost + 'api/centers', {headers: this.createHeaders()});
  }
  getLoggedUserCenter(userID: number): Observable<BloodBankResponse>{
    return this.http.get<BloodBankResponse>(this.apiHost + 'api/centers/loggedAdminCenter/' + userID.toString(), {headers: this.createHeaders()})
  }

  saveCenter(center: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/centers/loggedAdminCenter/saveCenter', center, {headers: this.createHeaders()})
  }

  getCentersAdmins(centerID: number): Observable<WholeUserResponse[]>{
    return this.http.get<WholeUserResponse[]>(this.apiHost + 'api/user/centersAdmins/' + centerID.toString(), {headers: this.createHeaders()})
  }
  
  getAllAvailableCenters(dateTimeDTO: any): Observable<any>{
    return this.http.post<CenterResponse[]>(this.apiHost + 'api/centers/getAllAvailableCenters', dateTimeDTO, {headers: this.createHeaders()})
  }

  getCentersSupply(centerID: number): Observable<BloodSupply[]>{
    return this.http.get<BloodSupply[]>(this.apiHost + 'api/blood/supply/' + '2', {headers: this.createHeaders()})
  }

  sendSpeed(speed: number) {
    return this.http.post<any>(this.apiHost+'api/coordinates/set_refresh_period',  speed ,{headers: this.createHeaders()})
  }
  
}