// user-type-guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { UserRolesGuards } from './user_guard';
import { AuthService } from './auth_service';

@Injectable({
  providedIn: 'root'
})
export class UserTypeGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.isAuthenticated()) {
      const userRoles: string[] | null = this.authService.getUserRoles();
      const requiredRole = route.data['requiredRole'] as UserRolesGuards;

      if (userRoles && userRoles.includes(requiredRole)) {
        return true;
      } else {
        // Ručno izazivamo "401 Unauthorized" grešku
        this.router.navigate(['/unautorized']);
      return false;
      }
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
