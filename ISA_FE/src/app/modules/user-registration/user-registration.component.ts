import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistratedUser } from '../model/user.model';
import { UserService } from '../services/user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent{

  constructor(private userService: UserService ,private router: Router) {}
  public user = new RegistratedUser();

  registrationForm= new FormGroup({
    name: new FormControl('',[Validators.required]),
    surname: new FormControl('',[Validators.required])
  })
  
  
  public registerUser() {
    this.userService.registerUser(this.user).subscribe(res => {
        return console.log("User is created!");
    });
  }

  get name()  {
    return this.registrationForm.get('name'); 
  }

  get surname()  {
    return this.registrationForm.get('surname');
    
  }


}
