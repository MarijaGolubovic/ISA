import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserResponse } from 'src/app/modules/model/user.model';
import { UserService } from 'src/app/modules/services/user.service';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public dataSource = new MatTableDataSource<UserResponse>();
  public displayedColumns = ['name', 'surname', 'email', 'phoneNumber'];
  public users: UserResponse[] = [];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getUserResponses().subscribe(res => {
      this.users = res;
      this.dataSource.data = this.users;
    })
  }
}