import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateAppointmentDTO } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';
import { NgToastService } from 'ng-angular-popup';


@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {

  app : CreateAppointmentDTO =  new CreateAppointmentDTO();
  message: string = ""
  minDate: string = ""

  constructor(private appService: AppointmentService, private router: Router, private alert: NgToastService) {
    this.app.bloodBankID = 1;
  }

  ngOnInit(): void {
    this.minDate = new Date().toISOString()
  }

  scheduleAppointment(){
    console.log(this.app)
    this.appService.isAppointmentAvailable(this.app).subscribe(res => {
      this.message = res
      if(this.message === 'Available'){
        this.appService.saveAppointment(this.app).subscribe(res => {
          this.alert.success({detail: 'Success!', summary: "You are successfully schedule appointment!", duration: 5000})
          alert("Success!")
        })
      }else{
        this.alert.warning({detail:"WARNING",summary:this.message,duration: 5000})
        alert(this.message)
      }
    })
  }
  // scheduleAppointment(){
    
  //       this.appService.saveAppointment(this.app).subscribe(res => {
  //         // this.alert.success({detail: 'Success!', summary: "You are successfully schedule appointment!", duration: 5000})
  //         alert("Success!")
  //       })
     
  // }
}
