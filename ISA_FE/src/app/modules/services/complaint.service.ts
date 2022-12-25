import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Complaint } from '../model/complaint';
import { WholeUserResponse, UserResponse, WholeUserResponseWithBloodBank, RegistratedUser } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  getAllComplaints(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(this.apiHost + 'api/complaints', {headers: this.headers});
  }


  getAllComplaintsOnPending(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(this.apiHost + 'api/complaints/pending', {headers: this.headers});
  }
}