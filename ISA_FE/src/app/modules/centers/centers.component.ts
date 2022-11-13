import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CenterService } from 'src/app/modules/services/centers.service';
import { CenterResponse } from 'src/app/modules/model/center.model';
import { MatTableDataSource } from '@angular/material/table';
import {Sort} from '@angular/material/sort';





@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent implements OnInit {

  public dataSource = new MatTableDataSource<CenterResponse>();
  public displayedColumns = ['name', 'city', 'street', 'streetNumber','grade'];
  public centers: CenterResponse[] = [];
  public booleanValueByName: any = false;
  public booleanValueByCity: any = false;
  public booleanValueByStreet: any = false;
  public booleanValueByStreetNumber: any = false;
  public booleanValueByGrade: any = false;
  public searchName: string = '';
  public searchCity: string = '';
  public searchMinGrade: number = 0;
  public searchMaxGrade: number = 10

  constructor(private centerService: CenterService, private router: Router) {
    this.sortedData = this.centers.slice();
   }

  sortedData: CenterResponse[];

  ngOnInit(): void {
    (this.centerService.getCenterResponses()).subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
      this.sortedData = this.centers.slice();
    })
  }


  sortFunctionByName(boolean:boolean) {
    if (boolean == true){
      this.centers.sort((a, b) => {
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
      this.dataSource.data = this.centers;
      this.booleanValueByName = !this.booleanValueByName;
    }
    else{
      this.centers.sort((a, b) => {
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
      this.dataSource.data = this.centers;
      this.booleanValueByName = !this.booleanValueByName;
    }
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


sortFunctionByCity(boolean:boolean) {
  if (boolean == true){
    this.centers.sort((a, b) => {
      const cityA = a.city.toUpperCase();
      const cityB = b.city.toUpperCase();
      if (cityA < cityB) {
        return -1;
      }
      if (cityA > cityB) {
        return 1;
      }
    
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByCity = !this.booleanValueByCity;
  }
  else{
    this.centers.sort((a, b) => {
      const cityA = a.city.toUpperCase();
      const cityB = b.city.toUpperCase(); 
      if (cityA > cityB) {
        return -1;
      }
      if (cityA < cityB) {
        return 1;
      }
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByCity = !this.booleanValueByCity;
  }
}

sortFunctionByStreet(boolean:boolean) {
  if (boolean == true){
    this.centers.sort((a, b) => {
      const streetA = a.street.toUpperCase();
      const streetB = b.street.toUpperCase();
      if (streetA < streetB) {
        return -1;
      }
      if (streetA > streetB) {
        return 1;
      }
    
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByStreet = !this.booleanValueByStreet;
  }
  else{
    this.centers.sort((a, b) => {
      const streetA = a.street.toUpperCase();
      const streetB = b.street.toUpperCase(); 
      if (streetA > streetB) {
        return -1;
      }
      if (streetA < streetB) {
        return 1;
      }
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByStreet = !this.booleanValueByStreet;
  }
}


sortFunctionByStreetNumber(boolean:boolean) {
  if (boolean == true){
    this.centers.sort((a, b) => {
      const streetNumberA = a.streetNumber;
      const streetNumberB = b.streetNumber;
      if (streetNumberA < streetNumberB) {
        return -1;
      }
      if (streetNumberA > streetNumberB) {
        return 1;
      }
    
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByStreetNumber = !this.booleanValueByStreetNumber;
  }
  else{
    this.centers.sort((a, b) => {
      const streetNumberA = a.streetNumber;
      const streetNumberB = b.streetNumber; 
      if (streetNumberA > streetNumberB) {
        return -1;
      }
      if (streetNumberA < streetNumberB) {
        return 1;
      }
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByStreetNumber = !this.booleanValueByStreetNumber;
  }
}

sortFunctionByGrade(boolean:boolean) {
  if (boolean == true){
    this.centers.sort((a, b) => {
      const gradeA = a.grade;
      const gradeB = b.grade;
      if (gradeA < gradeB) {
        return -1;
      }
      if (gradeA > gradeB) {
        return 1;
      }
    
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByGrade = !this.booleanValueByGrade;
  }
  else{
    this.centers.sort((a, b) => {
      const gradeA = a.grade;
      const gradeB = b.grade; 
      if (gradeA > gradeB) {
        return -1;
      }
      if (gradeA < gradeB) {
        return 1;
      }
      return 0;
    });
    this.dataSource.data = this.centers;
    this.booleanValueByGrade = !this.booleanValueByGrade;
  }
}




}


