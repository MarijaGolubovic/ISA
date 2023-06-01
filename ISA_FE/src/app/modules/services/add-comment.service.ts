import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Complaint } from '../model/complaint';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class AddCommentService {

  apiHost: string = 'http://localhost:8081/';
  // headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private createHeaders(): HttpHeaders {
    const token = this.authService.getAuthToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  constructor(private http: HttpClient, private authService: AuthService) { }

  replyOnComplaint(complaint: Complaint): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/complaints/update',complaint, {headers: this.createHeaders()})
  }
}
