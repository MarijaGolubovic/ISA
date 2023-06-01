import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QUestionnaireRespons } from '../model/questionnaire.model';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
    providedIn: 'root'
  })

export class QuestionnaireService{
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

    saveQuestionaire(questionnaire:QUestionnaireRespons): Observable<QUestionnaireRespons>{
      return this.http.put<QUestionnaireRespons>(this.apiHost + 'api/questionnaire', questionnaire, {headers: this.createHeaders()})
    }

    checkIfQuestionnaireHasBeenCompletedInLastSixMonths(userId: any){
      return this.http.post<boolean>(this.apiHost + 'api/questionnaire/checkIfQuestionnaireHasBeenCompletedInLastSixMonths', userId, {headers: this.createHeaders()})
    }

}