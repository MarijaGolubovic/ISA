import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WholeUserResponse, UserResponse, WholeUserResponseWithBloodBank, RegistratedUser, UserResponseBloodCenter } from '../model/user.model';
import { AuthService } from 'src/app/auth/auth_service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

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

  getUserResponses(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user', {headers: this.createHeaders()});
  }

  getAdminCenters(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user/admin-center', {headers: this.createHeaders()});
  }

  getUserResponsesForAdminCenter(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user/'+ 'admin', {headers: this.createHeaders()});
  }

  getDoneAppointmentsUser(): Observable<UserResponseBloodCenter[]> {
    return this.http.get<UserResponseBloodCenter[]>(this.apiHost + 'api/user/usersBlood/'+ '2', {headers: this.createHeaders()});
  }

  searchUsers(name:string,surname:string): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/user/search/'+ name + '/' + surname, {headers: this.createHeaders()});
  }

  getLoggedUser(): Observable<WholeUserResponse>{
    return this.http.get<WholeUserResponse>(this.apiHost + 'api/user/getLoggedUser', {headers: this.createHeaders()})
  }

  getLoggedUserWithBloodBank(): Observable<WholeUserResponseWithBloodBank>{
    return this.http.get<WholeUserResponseWithBloodBank>(this.apiHost + 'api/user/getLoggedUser', {headers: this.createHeaders()})
  }

  saveUser(user: any): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/saveUser', user, {headers: this.createHeaders()})
  }

  registerUser(user:RegistratedUser): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/registerUser', user, {headers: this.createHeaders()})
  }

  updateUserBB(user:UserResponse, idBB : String): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/updateBloodBankID/' + idBB, user, {headers: this.createHeaders()})
  }
  updateUserS(id:number): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/user/addStrikes/' + id.toString(), {headers: this.createHeaders()})
  }

}