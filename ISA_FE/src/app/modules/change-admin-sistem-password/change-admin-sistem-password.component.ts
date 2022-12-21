import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { WholeUserResponse } from '../model/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-change-admin-sistem-password',
  templateUrl: './change-admin-sistem-password.component.html',
  styleUrls: ['./change-admin-sistem-password.component.css']
})
export class ChangeAdminSistemPasswordComponent implements OnInit {

  public user: WholeUserResponse = new WholeUserResponse();
  public oldPass: string = '';
  public newPass: string = '';
  public confirmNewPass: string = '';
  public errorOldPass: string = '';
  public errorNewPass: string = '';
  public disable : boolean = true;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe(result =>{
      this.user = result;
      
      if(result == null){
        alert("Log in!");
        return;
      }
    });
  }

  public validation(){
    this.errorNewPass = ''
    this.errorOldPass = ''

    if(this.user.password != this.oldPass){
      this.errorOldPass = 'Password is not valid!' 
        return false;
    }else if(this.newPass.length < 8){
      this.errorNewPass = 'Password must have minimum 8 characters!'
        return false;
    }else if(this.newPass != this.confirmNewPass){
      this.errorNewPass = 'Passwords do not match!'
        return false;
    }
    return true;
  }

  public changePassword() {
    this.user.password = this.newPass;

    this.userService.saveUser(this.user).subscribe(res =>{
      alert("Password successful changed!");
      window.location.reload();
    });
  }

}
