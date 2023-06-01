import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Complaint } from '../model/complaint';
import { WholeUserResponse, UserResponse, WholeUserResponseWithBloodBank, RegistratedUser } from '../model/user.model';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

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

  getAllComplaints(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(this.apiHost + 'api/complaints', {headers: this.createHeaders()});
  }


  getAllComplaintsOnPending(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(this.apiHost + 'api/complaints/pending', {headers: this.createHeaders()});
  }
}