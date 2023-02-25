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
    nameAdmin: new FormControl('',[Validators.required]),
    surname: new FormControl('',[Validators.required]),
    email: new FormControl('',Validators.required),
    gender:new FormControl(null,[Validators.required]),
    URN:new FormControl('',[Validators.required]),
    address: new FormControl('',[Validators.required]),
    streetNumber: new FormControl('',[Validators.required]),
    cityAdmin: new FormControl('',[Validators.required]),
    countryAdmin: new FormControl('',[Validators.required]),
    infoAboutInstitution: new FormControl('',[Validators.required]),
  })

  public createBloodBank() {
    this.user.bloodBank = this.bloodBankRequest;
    this.user.password = 'password';
    this.userService.saveUser(this.user).subscribe(res => {
      alert("Successfull!")
    });
  }

  get name()  {
    return this.registrationForm.get('name'); 
  }

  get description()  {
    return this.registrationForm.get('description'); 
  }

  get street()  {
    return this.registrationForm.get('street'); 
  }

  get number()  {
    return this.registrationForm.get('number'); 
  }

  get nameAdmin()  {
    return this.registrationForm.get('nameAdmin'); 
  }

  get surname()  {
    return this.registrationForm.get('surname'); 
  }

  get email(){
    return this.registrationForm.get('email'); 
  }

  get psw(){
    return this.registrationForm.get('psw'); 
  }

  get gender(){
    return this.registrationForm.get('gender'); 
  }

  get URN(){
    return this.registrationForm.get('URN'); 
  }

  get address(){
    return this.registrationForm.get('address'); 
  }

  get streetNumber(){
    return this.registrationForm.get('streetNumber'); 
  }

  get city(){
    return this.registrationForm.get('city'); 
  }

  get country(){  
    return this.registrationForm.get('country'); 
  }

  get cityAdmin(){
    return this.registrationForm.get('cityAdmin'); 
  }

  get countryAdmin(){  
    return this.registrationForm.get('countryAdmin'); 
  }

  get phoneNumber(){
    return this.registrationForm.get('phoneNumber'); 
  }

  get profession(){
    return this.registrationForm.get('profession'); 
  }

  get infoAboutInstitution(){ 
    return this.registrationForm.get('infoAboutInstitution'); 
  }

  public isValid(name:string){
    let sampleRegEx: RegExp = /[A-Z][A-Za-z]+/;
    return sampleRegEx.test(name);
  }

  public isValidEmail(email:string){
    let sampleRegEx: RegExp = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    return sampleRegEx.test(email);
  }

  isURNValid(){
    let isValidUrn:boolean=false;
    let sampleRegEx: RegExp = /[0-9]{13}/;
    return sampleRegEx.test(this.user.URN);
  }

  isPhoneNumberValid(){
    let isValidPhoneNumber:boolean=false;
    let sampleRegEx: RegExp = /[0-9]+/;
    if(sampleRegEx.test(this.user.phoneNumber))
      if(this.user.phoneNumber.length>3)
        isValidPhoneNumber=true;

    return isValidPhoneNumber;
  }

}
