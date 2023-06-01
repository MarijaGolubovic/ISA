import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  email: string | undefined;
  password: string | undefined;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  login(): void {
    const data = {
      email: this.email,
      password: this.password
    };

    this.http.post('/login', data).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.error(error);
      }
    );
  }
}
