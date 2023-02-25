import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, Subscription } from 'rxjs';
import { BloodBankRequest } from '../model/bloodBankRequest.model';
import { WholeUserResponse } from '../model/user.model';


@Injectable({
  providedIn: 'root'
})
export class BloodbankService {

  apiHost: string = 'http://localhost:8081/';

  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  registerBloodBankWithAdmin(bloodBank: BloodBankRequest): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/bloodbank/register', bloodBank, {headers: this.headers});
  }
  
  getAll(): Observable<any> {
    return this.http.get<any>(this.apiHost + 'api/bloodbank', {headers: this.headers});
  }

}
