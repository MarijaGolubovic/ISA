import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistratedUser } from '../model/user.model';
import { UserService } from '../services/user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { transition } from '@angular/animations';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent{

  constructor(private userService: UserService ,private router: Router) {}
  public user = new RegistratedUser();
  public repeat='';

  registrationForm= new FormGroup({
    name: new FormControl('',[Validators.required]),
    surname: new FormControl('',[Validators.required]),
    email: new FormControl('',Validators.required),
    psw: new FormControl('',[Validators.required]),
    gender:new FormControl(null,[Validators.required]),
    URN:new FormControl('',[Validators.required]),
    address: new FormControl('',[Validators.required]),
    streetNumber: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    country: new FormControl('',[Validators.required]),
    phoneNumber: new FormControl('',[Validators.required]),
    profession: new FormControl('',[Validators.required]),
    infoAboutInstitution: new FormControl('',[Validators.required]),
  })
  
  

  async redirectToUsers() : Promise<void>{
    await this.router.navigateByUrl('/users')
  }

  public registerUser(){
    this.userService.registerUser(this.user).subscribe(res => {
        this.redirectToUsers();
        return console.log("User is created!");
    });
  }

  get name()  {
    return this.registrationForm.get('name'); 
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

  public isLongPassword(password:string){
    let isLong:boolean=false;
    if(password.length >=5)
      isLong=true;

    return isLong;
  }

  repeatPassword(e:any) {
    this.repeat = e.target.value;
  }

  isRepatPasswordIsEmpty(){
    let repeatPsswordIsEmpty:boolean=true;
      if(this.repeat.length>0) 
        repeatPsswordIsEmpty=false
    return repeatPsswordIsEmpty;
  }

  isPasswordCorrect(){
    let isCorrectPassword:boolean=false;
    if(this.repeat === this.user.password)
      isCorrectPassword=true;

    return isCorrectPassword;
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

  isAllValid(){
    let allIsValid:boolean = false;
    let nameValid:boolean = false;
    let surnameValid:boolean = false;
    let emailValid:boolean = false;
    let longPasswordValid:boolean = false;
    let passwordCorrect:boolean = false;
    let genderValid:boolean=false;
    let urnValid:boolean = false;
    let phoneValid:boolean = false;
    
    if((this.isValid(this.user.name)))
        nameValid=true;

    if(this.isValid(this.user.email))
      surnameValid=true;

    if(this.isValidEmail(this.user.email))
      emailValid=true;

    if(this.isLongPassword(this.user.password))
      longPasswordValid=true;

    if(this.isPasswordCorrect())
      passwordCorrect=true;

    if(this.user.gender !=null)
      genderValid=true;
    
    if(this.isURNValid())
        genderValid=true;
    
    if(this.isPhoneNumberValid())
      phoneValid=true;
    
    allIsValid= nameValid && surnameValid && emailValid && longPasswordValid && passwordCorrect && genderValid && urnValid && phoneValid;
    
    return allIsValid
  }


}
