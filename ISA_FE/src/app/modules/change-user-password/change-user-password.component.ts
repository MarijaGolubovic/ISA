import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { WholeUserResponse } from '../model/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-change-user-password',
  templateUrl: './change-user-password.component.html',
  styleUrls: ['./change-user-password.component.css']
})
export class ChangeUserPasswordComponent{

  public user: WholeUserResponse = new WholeUserResponse();
  public urnF: string = '';
  public oldPass: string = '';
  public newPass: string = '';
  public confirmNewPass: string = '';
  public errorURN: string = '';
  public errorOldPass: string = '';
  public errorNewPass: string = '';

  public changePasswordForm = new FormGroup({
    urn: new FormControl('', [Validators.required]),
    oldPassword: new FormControl('',[Validators.required]),
    newPassword: new FormControl('',[Validators.required]),
    repeatedNewPassword: new FormControl('',[Validators.required])
  })

  constructor(private userService: UserService, private router: Router) { }

  get urn(){
    return this.changePasswordForm.get('urn')
  }

  get oldPassword(){
    return this.changePasswordForm.get('oldPassword')
  }

  get newPassword(){
    return this.changePasswordForm.get('newPassword')
  }

  get repeatedNewPassword(){
    return this.changePasswordForm.get('repeatedNewPassword')
  }

  public changePassword() {

    this.userService.getLoggedUser().subscribe(result =>{
      this.user = result;
      if(result == null){
        this.errorURN = 'URN is not valid!'
        return;
      }
      this.errorNewPass = ''
      this.errorOldPass = ''
      this.errorURN = ''

      if(this.user.password != this.oldPass){
        this.errorOldPass = 'Password is not valid!' 
          return;
      }else if(this.newPass.length < 8){
        this.errorNewPass = 'Password must have minimum 8 characters!'
          return;
      }else if(this.newPass != this.confirmNewPass){
        this.errorNewPass = 'Passwords do not match!'
          return;
      }

      this.user.password = this.newPass;

      this.userService.saveUser(this.user).subscribe(res =>{
        this.changePasswordForm.reset(this.changePasswordForm.value);
        window.location.reload();
        alert("Password successful changed!");
      });

    });
  }
}



