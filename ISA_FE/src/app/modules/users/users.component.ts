import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserResponse } from 'src/app/modules/model/user.model';
import { UserService } from 'src/app/modules/services/user.service';
import {MatIconModule} from '@angular/material/icon';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

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
    this.userService.getUserResponses().subscribe(res => {
      this.users = res;
      this.dataSource.data = this.users;
    })
  }

  public searchUsers() {
    this.userService.searchUsers(this.searchName,this.searchSurname).subscribe(res => {
        this.users = res;
        this.dataSource.data = this.users;
    });
  }
}