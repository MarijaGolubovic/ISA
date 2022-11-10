import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterService } from 'src/app/modules/services/centers.service';
import { CenterResponse } from 'src/app/modules/model/center.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent implements OnInit {

  public dataSource = new MatTableDataSource<CenterResponse>();
  public displayedColumns = ['name', 'city', 'street', 'grade'];
  public centers: CenterResponse[] = [];

  constructor(private centerService: CenterService, private router: Router) { }


  ngOnInit(): void {
    (this.centerService.getCenterResponses()).subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
  }

}
