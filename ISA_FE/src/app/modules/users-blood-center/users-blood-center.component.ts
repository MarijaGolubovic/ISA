import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserResponseBloodCenter } from '../model/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users-blood-center',
  templateUrl: './users-blood-center.component.html',
  styleUrls: ['./users-blood-center.component.css']
})
export class UsersBloodCenterComponent implements OnInit {
  public dataSource = new MatTableDataSource<UserResponseBloodCenter>();
  public displayedColumns = ['name', 'surname', 'email', 'date'];
  public users: UserResponseBloodCenter[] = [];
  public searchName = '';
  public searchSurname = '';
  public booleanValueByName: any = false;
  public booleanValueBySurname: any = false;
  public booleanValueByEmail: any = false;
  public booleanValueByDate: any = false;
  
  constructor(private userService: UserService, private router: Router) { }

  public searchForm = new FormGroup({
    searchName: new FormControl('', [Validators.required]),
    searchSurname: new FormControl('',[Validators.required])
  })

  ngOnInit(): void {
    const that = this;
    this.userService.getDoneAppointmentsUser().subscribe(res => {
      this.users = res;
      this.dataSource.data = this.users;
    })
  }

  

  public searchUsers() {
    const tmp = [];
    const that = this;
    for(let i = 0; i< that.users.length; i++) {
      if (that.users[i].name.match(this.searchName) != null ){
        if (that.users[i].surname.match(this.searchSurname) != null ){
          tmp.push(that.users[i]);
        }
      }
    }
    this.dataSource.data = tmp; 
  }

  sortFunctionByName(boolean:boolean) {
    if (boolean == true){
      this.users.sort((a, b) => {
        const nameA = a.name.toUpperCase();
        const nameB = b.name.toUpperCase();
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueByName = !this.booleanValueByName;
    }
    else{
      this.users.sort((a, b) => {
        const nameA = a.name.toUpperCase();
        const nameB = b.name.toUpperCase(); 
        if (nameA > nameB) {
          return -1;
        }
        if (nameA < nameB) {
          return 1;
        }
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueByName = !this.booleanValueByName;
    }
  }

  sortFunctionBySurname(boolean:boolean) {
    if (boolean == true){
      this.users.sort((a, b) => {
        const nameA = a.surname.toUpperCase();
        const nameB = b.surname.toUpperCase();
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueBySurname = !this.booleanValueBySurname;
    }
    else{
      this.users.sort((a, b) => {
        const nameA = a.surname.toUpperCase();
        const nameB = b.surname.toUpperCase(); 
        if (nameA > nameB) {
          return -1;
        }
        if (nameA < nameB) {
          return 1;
        }
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueBySurname = !this.booleanValueBySurname;
    }
  }

  sortFunctionByEmail(boolean:boolean) {
    if (boolean == true){
      this.users.sort((a, b) => {
        const nameA = a.email.toUpperCase();
        const nameB = b.email.toUpperCase();
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueByEmail = !this.booleanValueByEmail;
    }
    else{
      this.users.sort((a, b) => {
        const nameA = a.email.toUpperCase();
        const nameB = b.email.toUpperCase(); 
        if (nameA > nameB) {
          return -1;
        }
        if (nameA < nameB) {
          return 1;
        }
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueByEmail = !this.booleanValueByEmail;
    }
  }

  sortFunctionByDate(boolean:boolean) {
    if (boolean == true){
      this.users.sort((a, b) => {
        const nameA = a.email.toUpperCase();
        const nameB = b.email.toUpperCase();
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueByDate = !this.booleanValueByDate;
    }
    else{
      this.users.sort((a, b) => {
        const nameA = a.email.toUpperCase();
        const nameB = b.email.toUpperCase(); 
        if (nameA > nameB) {
          return -1;
        }
        if (nameA < nameB) {
          return 1;
        }
        return 0;
      });
      this.dataSource.data = this.users;
      this.booleanValueByDate = !this.booleanValueByDate;
    }
  }



  async profil() : Promise<void>{
    await this.router.navigateByUrl('/users/edit')
  }

  async appointments() : Promise<void>{
    await this.router.navigateByUrl('/appointmentBB')
  }

  async ShedulApp() : Promise<void>{
    await this.router.navigateByUrl('/appointment/create')
  }

  async bloodSupply() : Promise<void>{
    await this.router.navigateByUrl('/bloodSupply')
  }

  async calendar() : Promise<void>{
    await this.router.navigateByUrl('/calendar')
  }

}
