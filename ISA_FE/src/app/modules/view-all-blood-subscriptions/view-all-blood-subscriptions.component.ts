import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AmountOfBloodTypeDTO, BloodSubscriptionDTO } from '../model/bloodSubscription.model';
import { BloodSubsctiprionServiceService } from '../services/blood-subsctiprion-service.service';

@Component({
  selector: 'app-view-all-blood-subscriptions',
  templateUrl: './view-all-blood-subscriptions.component.html',
  styleUrls: ['./view-all-blood-subscriptions.component.css']
})
export class ViewAllBloodSubscriptionsComponent implements OnInit {

  public dataSource = new MatTableDataSource<BloodSubscriptionDTO>();
  public displayedColumns = ['BloodBankName', 'Date', 'View'];

  constructor(private service: BloodSubsctiprionServiceService, private router: Router) { }

  ngOnInit(): void {
    this.service.getAllForLoggedAdministrator().subscribe(res =>{
      this.dataSource.data = res;
    })
  }

  viewSubscription(bs: BloodSubscriptionDTO){
    let bloodSub : string = '';
    for(let aobt of bs.amountOfBloodTypes){
      bloodSub += aobt.bloodType.toString() + " :" + aobt.amount + " units\n"; 
    }
    alert(bloodSub);
  }

}
