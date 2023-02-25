import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WholeUserResponse, UserResponse, WholeUserResponseWithBloodBank, RegistratedUser, UserResponseBloodCenter } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({'Access-Control-Allow-Origin': '*' });

  constructor(private http: HttpClient) { }

  getUserResponses(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user', {headers: this.headers});
  }

  getAdminCenters(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user/admin-center', {headers: this.headers});
  }

  getUserResponsesForAdminCenter(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user/'+ 'admin', {headers: this.headers});
  }

  getDoneAppointmentsUser(): Observable<UserResponseBloodCenter[]> {
    return this.http.get<UserResponseBloodCenter[]>(this.apiHost + 'api/user/usersBlood/'+ '2', {headers: this.headers});
  }

  searchUsers(name:string,surname:string): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user/search/'+ name + '/' + surname, {headers: this.headers});
  }

  getLoggedUser(): Observable<WholeUserResponse>{
    return this.http.get<WholeUserResponse>(this.apiHost + 'api/user/getLoggedUser', {headers: this.headers})
  }

  getLoggedUserWithBloodBank(): Observable<WholeUserResponseWithBloodBank>{
    return this.http.get<WholeUserResponseWithBloodBank>(this.apiHost + 'api/user/getLoggedUser', {headers: this.headers})
  }

  saveUser(user: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/saveUser', user, {headers: this.headers})
  }

  registerUser(user:RegistratedUser): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/registerUser', user, {headers: this.headers})
  }

  updateUserBB(user:UserResponse, idBB : String): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/updateBloodBankID/' + idBB, user, {headers: this.headers})
  }
  updateUserS(id:number): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/addStrikes/' + id.toString(), {headers: this.headers})
  }

}