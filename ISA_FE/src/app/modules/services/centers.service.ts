import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {CenterResponse } from '../model/center.model';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  public getCenterResponses(): Observable<CenterResponse[]> {
    return this.http.get<CenterResponse[]>(this.apiHost + 'api/centers', {headers: this.headers});
  }
}