import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Complaint } from '../model/complaint';

@Injectable({
  providedIn: 'root'
})
export class AddCommentService {

  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  replyOnComplaint(complaint: Complaint): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/complaints/update',complaint, {headers: this.headers})
  }
}
