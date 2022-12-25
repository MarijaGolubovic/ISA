import { Component, OnInit } from '@angular/core';
import { MatTableDataSource} from '@angular/material/table';
import { MatSelectModule} from '@angular/material/select';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BloodBankResponse } from '../model/bloodBank.model';
import { BloodbankService } from '../services/register-bloodbank.service';
import { UserService } from '../services/user.service';
import { UserResponse, WholeUserResponseWithBloodBank } from '../model/user.model';

@Component({
  selector: 'add-admin-center.component',
  templateUrl: './add-admin-center.component.html',
  styleUrls: ['./add-admin-center.component.css']
})
export class AddAdminCenterComponent implements OnInit {

  bloodbank: BloodBankResponse | undefined;
  states: BloodBankResponse[] = [];
  users: UserResponse[] = [];
  public selectedBloodBank : BloodBankResponse = new BloodBankResponse;
  public selectedUser : UserResponse = new UserResponse;
  public register : boolean = false;
  public user : WholeUserResponseWithBloodBank = new WholeUserResponseWithBloodBank;

  constructor(private router: Router, private service: BloodbankService, private userService: UserService) { }

  public registrationForm = new FormGroup({
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

  ngOnInit(): void {
    this.service.getAll().subscribe(result=> {
        this.states = result;
      });
    this.userService.getAdminCenters().subscribe(result=>{
        this.users = result;
    });
  }

  public submit(){
    this.userService.updateUserBB(this.selectedUser, this.selectedBloodBank.id.toString()).subscribe(result=> {
        alert("You added Admin Center!");
      });
  }

  public createAdmin(){
    this.user.bloodBank = this.selectedBloodBank;
    this.user.password = 'password';
    this.userService.saveUser(this.user).subscribe(res => {
      alert("Successfull!")
    });
  }

  public show(){
    this.register= true;
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
