import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

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

  sendNews(news: any): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/news', news, {headers: this.createHeaders()})
  }
}
