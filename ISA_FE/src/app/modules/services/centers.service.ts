import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankResponse } from '../model/bloodBank.model';
import {CenterResponse } from '../model/center.model';
import { WholeUserResponse } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  public getCenterResponses(): Observable<CenterResponse[]> {
    return this.http.get<CenterResponse[]>(this.apiHost + 'api/centers', {headers: this.headers});
  }
  getLoggedUserCenter(userID: number): Observable<BloodBankResponse>{
    return this.http.get<BloodBankResponse>(this.apiHost + 'api/centers/loggedAdminCenter/' + userID.toString(), {headers: this.headers})
  }

  saveCenter(center: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/centers/loggedAdminCenter/saveCenter', center, {headers: this.headers})
  }

  getCentersAdmins(centerID: number): Observable<WholeUserResponse[]>{
    return this.http.get<WholeUserResponse[]>(this.apiHost + 'api/user/centersAdmins/' + centerID.toString(), {headers: this.headers})
  }
  
  getAllAvailableCenters(dateTimeDTO: any): Observable<any>{
    return this.http.post<CenterResponse[]>(this.apiHost + 'api/centers/getAllAvailableCenters', dateTimeDTO, {headers: this.headers})
  }
}