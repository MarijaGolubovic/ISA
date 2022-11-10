import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { WholeUserResponse, UserResponse } from '../model/user.model';
import { UserService } from '../services/user.service';
import {MatRadioModule} from '@angular/material/radio';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  public loggedUser: WholeUserResponse = new WholeUserResponse()

  public errorCounter: number = 0;
  public nameError: string = ''
  public surnameError: string = ''
  public phoneError: string = ''
  public countryError: string = ''
  public cityError: string = ''
  public streetError: string = ''
  public numberError: string = ''
  public professionError: string = ''
  public informationError: string = ''

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe(res => {
      this.loggedUser = res;
    })
  }

  public saveUser(){
    this.errorCounter = 0;

    if(this.loggedUser.name == ''){
      this.nameError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.surname == ''){
      this.surnameError = 'This field can not be empty!'
      this.errorCounter++
    }
    let regex = /^\+3816\d\d\d\d\d\d\d\d$/i;
    if(!regex.test(this.loggedUser.phoneNumber)){
      this.phoneError = 'Phone number must be in this format: +38166-------'
      this.errorCounter++
    }
    if(this.loggedUser.phoneNumber == ''){
      this.phoneError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.address.country == ''){
      this.countryError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.address.city == ''){
      this.cityError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.address.street == ''){
      this.streetError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.address.number == ''){
      this.numberError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.profession == ''){
      this.professionError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.loggedUser.infoAboutInstitution == ''){
      this.informationError = 'This field can not be empty!'
      this.errorCounter++
    }
    if(this.errorCounter > 0){
      return;
    }

    this.userService.saveUser(this.loggedUser).subscribe(res =>{
      this.nameError = ''
      this.surnameError = ''
      this.phoneError = ''
      this.countryError = ''
      this.cityError = ''
      this.streetError = ''
      this.numberError = ''
      this.professionError = ''
      this.informationError = ''

      alert("Successfull!")
    })
  }

}