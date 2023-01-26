import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { BloodSupply } from '../model/bloodSupply.model';
import { CenterService } from '../services/centers.service';

@Component({
  selector: 'app-blood-supply',
  templateUrl: './blood-supply.component.html',
  styleUrls: ['./blood-supply.component.css']
})
export class BloodSupplyComponent implements OnInit {
  public dataSource = new MatTableDataSource<BloodSupply>();
  public displayedColumns = ['type', 'quantity'];
  constructor(private router: Router, private centerService: CenterService) { }

  ngOnInit(): void {
    this.centerService.getCentersSupply(2).subscribe(res => {
      this.dataSource.data = res;
    })
  }


  public bloodTypeToString(type:number){
    if (type == 0){
        return "A+";
    }else if (type == 1){
        return "A-";
    }else if (type == 2){
        return "B+";
    }else if (type == 3){
        return "B-";
    }else if (type == 4){
        return "AB+";
    }else if (type == 5){
        return "AB-";
    }else if (type == 6){
        return "O+";
    }else if (type == 7){
        return "O-";
    }else{
        return "UNKNOWN";
    }
  }
}
