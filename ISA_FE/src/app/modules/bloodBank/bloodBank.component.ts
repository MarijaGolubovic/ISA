import { Time } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { MatTableDataSource } from "@angular/material/table";
import { Router } from "@angular/router";
import { async } from "rxjs";
import { BloodBankResponse } from "../model/bloodBank.model";
import { CenterResponse } from "../model/center.model";
import { WholeUserResponse } from "../model/user.model";
import { CenterService } from "../services/centers.service";
import { UserService } from "../services/user.service";

@Component({
    selector: 'app-edit-bloodBank',
    templateUrl: './bloodBank.component.html',
    styleUrls: ['./bloodBank.component.css']
  })

  export class EditBloodBankComponent implements OnInit {
    public loggedUser: WholeUserResponse = new WholeUserResponse();
    public loggedUserCenter : BloodBankResponse = new BloodBankResponse();
    public administrators: WholeUserResponse[] | undefined; 
    public errorCounter: number = 0;
    public nameError: string = '';
    public descriptonError: string = '';
    public averageRateError: string = "";
    public countryError: string = '';
    public cityError: string = '';
    public streetError: string = '';
    public numberError: string = '';
    public dataSource = new MatTableDataSource<WholeUserResponse>();
    public displayedColumns = ['name', 'surname', 'email', 'phoneNumber'];
    public searchName = '';
    public searchSurname = '';


    constructor(private userService: UserService, private router: Router, private centerService : CenterService) {
      
     }

     public searchForm = new FormGroup({
      searchName: new FormControl('', [Validators.required]),
      searchSurname: new FormControl('',[Validators.required])
    })
    ngOnInit(): void {
      this.userService.getLoggedUser().subscribe(res => {
        this.loggedUser = res;
        this.centerService.getLoggedUserCenter(this.loggedUser.id).subscribe (res => {
          this.loggedUserCenter = res;

            this.centerService.getCentersAdmins(this.loggedUserCenter.id).subscribe (res=> {
              this.administrators=res;
              this.dataSource.data = this.administrators;
            })
        })
      })     
    }


    public saveCenter(){ 
      this.errorCounter = 0;
      if(this.loggedUserCenter.name == ''){
        this.nameError = 'This field can not be empty!'
        this.errorCounter++
      }
      if(this.loggedUserCenter.description == ''){
        this.descriptonError = 'This field can not be empty!'
        this.errorCounter++
      }
      let regex = /([0-9]{1}(\.[0-9]){0,1})|10$/i;
      if(!regex.test(this.loggedUserCenter.averageRate.toString())){
        this.averageRateError = 'Average rate must be in range 0-10!'
        this.errorCounter++
      }
      if(this.loggedUserCenter.address.country == ''){
        this.countryError = 'This field can not be empty!'
        this.errorCounter++
      }
      if(this.loggedUserCenter.address.city == ''){
        this.cityError = 'This field can not be empty!'
        this.errorCounter++
      }
      if(this.loggedUserCenter.address.street == ''){
        this.streetError = 'This field can not be empty!'
        this.errorCounter++
      }
      if(this.loggedUserCenter.address.number == ''){
        this.numberError = 'This field can not be empty!'
        this.errorCounter++
      }
      if(this.errorCounter > 0){
        return;
      }

      this.centerService.saveCenter(this.loggedUserCenter).subscribe(res =>{
        
        this.nameError = '';
        this.descriptonError= '';
        this.averageRateError= "";
        this.countryError = '';
        this.cityError= '';
        this.streetError = '';
        this.numberError = '';
  
        alert("Successfull!")
      })
      
    }

    
  }