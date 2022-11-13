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


  sortData(sort: Sort) {
    const data = this.centers.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        // case 'calories':
        //   return compare(a.calories, b.calories, isAsc);
        // case 'fat':
        //   return compare(a.fat, b.fat, isAsc);
        // case 'carbs':
        //   return compare(a.carbs, b.carbs, isAsc);
        // case 'protein':
        //   return compare(a.protein, b.protein, isAsc);
        default:
          return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

