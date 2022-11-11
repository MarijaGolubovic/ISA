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
  public displayedColumns = ['name', 'city', 'street', 'streetNumber','grade'];
  public centers: CenterResponse[] = [];
  public searchName: string = '';
  public searchCity: string = '';
  public searchMinGrade: number = 0;
  public searchMaxGrade: number = 10

  constructor(private centerService: CenterService, private router: Router) { }


  ngOnInit(): void {
    (this.centerService.getCenterResponses()).subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
  }

  public searchCenters(){
    this.dataSource.data = []
    let searchedCenters: CenterResponse[] = [];
    for(let center of this.centers){
      if(center.name.toLocaleLowerCase().includes(this.searchName.toLocaleLowerCase()) && center.city.toLocaleLowerCase().includes(this.searchCity.toLocaleLowerCase())){
        searchedCenters.push(center);
      }
    }
    this.dataSource.data = searchedCenters;
  }

  public filterCenters(){
    let filteredCenters: CenterResponse[] = [];
    if(this.searchMaxGrade == 0){
      this.searchMaxGrade = 10
    }
    for(let center of this.dataSource.data){
      if(this.searchMinGrade <= center.grade && this.searchMaxGrade >= center.grade){
        filteredCenters.push(center);
      }
    }
    this.dataSource.data =filteredCenters;
  }
}
