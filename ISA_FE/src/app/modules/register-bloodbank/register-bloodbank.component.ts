import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BloodBankRequest } from '../model/bloodbank.model';
import { BloodbankService } from '../services/register-bloodbank.service';

@Component({
  selector: 'app-register-bloodbank',
  templateUrl: './register-bloodbank.component.html',
  styleUrls: ['./register-bloodbank.component.css']
})
export class BloodbankRegistrationComponent{

  constructor(private bloodBankService: BloodbankService, private router: Router) { }

  public bloodBankRequest = new BloodBankRequest();

  public registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('',[Validators.required]),
    country: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    street: new FormControl('',[Validators.required]),
    number: new FormControl('',[Validators.required])
  })

  public createBloodBank() {
    this.bloodBankService.registerBloodBank(this.bloodBankRequest).subscribe(res => {
      if (res == true){
        return console.log("Blood bank is created!");
      }
    });
  }


}
