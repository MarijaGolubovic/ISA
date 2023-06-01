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

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login(this.email, this.password).subscribe(
      (response: any) => {
        this.authService.storeAuthToken(response.token); 
        this.router.navigate(['/']); 
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
