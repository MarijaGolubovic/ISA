import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Dodajte Router
import { AuthService } from 'src/app/auth/auth_service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  email: string = "";
  password: string = "";

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login(this.email, this.password).subscribe(
      (response: any) => {
        this.authService.storeAuthToken(response.token); 
        const userRoles = this.authService.getUserRoles();
        if (userRoles !== null && userRoles.includes('ROLE_REGISTERED')) {
          this.router.navigate(['allCenters']); 
        }else if(userRoles !== null && userRoles.includes('ROLE_ADMIN_CENTER')) {
          this.router.navigate(['calendar']); 
        }else if(userRoles !== null && userRoles.includes('ROLE_ADMIN_SISTEM')) {
          this.router.navigate(['add-admin-centar']); 
        }

        
        
      },
      (error: any) => {
        console.error(error);
        alert("Check your credentials or activate your account (check e-mail)!")
      }
    );
  }
}
