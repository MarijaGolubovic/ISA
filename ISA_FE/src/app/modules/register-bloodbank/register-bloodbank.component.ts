import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BloodBankRequest } from '../model/bloodBankRequest.model';
import { WholeUserResponse, WholeUserResponseWithBloodBank } from '../model/user.model';
import { BloodbankService } from '../services/register-bloodbank.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register-bloodbank',
  templateUrl: './register-bloodbank.component.html',
  styleUrls: ['./register-bloodbank.component.css']
})
export class BloodbankRegistrationComponent{

  constructor(private bloodBankService: BloodbankService, private userService: UserService ,private router: Router) { }

  public bloodBankRequest = new BloodBankRequest();
  public user = new WholeUserResponseWithBloodBank();

  public registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('',[Validators.required]),
    country: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    street: new FormControl('',[Validators.required]),
    number: new FormControl('',[Validators.required]),
  })

  public createBloodBank() {
    this.user.bloodBank = this.bloodBankRequest;
    this.user.password = 'password';
    this.userService.saveUser(this.user).subscribe(res => {
      if (res == true){
        return console.log("Admin is created!");
      }
    });
  }


}
