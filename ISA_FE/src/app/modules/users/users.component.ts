import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserResponse, UserType, WholeUserResponse, WholeUserResponseWithBloodBank } from 'src/app/modules/model/user.model';
import { UserService } from 'src/app/modules/services/user.service';
import {MatIconModule} from '@angular/material/icon';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public loggedUser: WholeUserResponseWithBloodBank = new WholeUserResponseWithBloodBank()
  
  public dataSource = new MatTableDataSource<UserResponse>();
  public displayedColumns = ['name', 'surname', 'email', 'phoneNumber'];
  public users: UserResponse[] = [];
  public searchName = '';
  public searchSurname = '';

  constructor(private userService: UserService, private router: Router) { }

  public searchForm = new FormGroup({
    searchName: new FormControl('', [Validators.required]),
    searchSurname: new FormControl('',[Validators.required])
  })

  ngOnInit(): void {
    const that = this;
    this.userService.getUserResponsesForAdminCenter().subscribe(res => {
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
}