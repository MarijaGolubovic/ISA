import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth_service';
import { Router } from '@angular/router'; // Dodajte Router

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  isLoggedIn(): boolean {
    return this.authService.isAuthenticated();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(["/login"])
  }

  isAdminCenter(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles !== null && userRoles.includes('ROLE_ADMIN_CENTER');
}

isAdminSistem(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles !== null && userRoles.includes('ROLE_ADMIN_SISTEM');
}

isRegisteredUser(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles !== null && userRoles.includes('ROLE_REGISTERED');
}

isUnregisteredUser(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles !== null &&  userRoles.includes('ROLE_UNREGISTERED');
}

isMedicalStaff(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles !== null &&  userRoles.includes('ROLE_MEDICAL_STUFF');
}



}
