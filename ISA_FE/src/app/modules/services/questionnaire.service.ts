import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QUestionnaireRespons } from '../model/questionnaire.model';

@Injectable({
    providedIn: 'root'
  })

export class QuestionnaireService{
  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

    saveQuestionaire(questionnaire:QUestionnaireRespons): Observable<QUestionnaireRespons>{
      return this.http.put<QUestionnaireRespons>(this.apiHost + 'api/questionnaire', questionnaire, {headers: this.headers})
    }

    checkIfQuestionnaireHasBeenCompletedInLastSixMonths(userId: any){
      return this.http.post<boolean>(this.apiHost + 'api/questionnaire/checkIfQuestionnaireHasBeenCompletedInLastSixMonths', userId, {headers: this.headers})
    }

}