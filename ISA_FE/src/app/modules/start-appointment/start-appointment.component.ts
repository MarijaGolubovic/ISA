import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AppointmentDTO, AppointmentUserDTO } from '../model/appointment.model';
import { QUestionnaireRespons } from '../model/questionnaire.model';
import { BloodType, Survey } from '../model/Survey.model';
import { AppointmentService } from '../services/appointment.service';
import { QuestionnaireService } from '../services/questionnaire.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-start-appointment',
  templateUrl: './start-appointment.component.html',
  styleUrls: ['./start-appointment.component.css']
})
export class StartAppointmentComponent implements OnInit {
  public appointment : AppointmentUserDTO = new AppointmentUserDTO();
  public id: number = 0 ;
  public proceedEx: boolean = false;
  public selectedBloodType : BloodType | undefined  ; 
  public survey: Survey = new Survey();
  states: BloodType[] = [0,1,2,3,4,5,6,7];

  constructor(private questionnaireService: QuestionnaireService,private router: Router, private route: ActivatedRoute, private appointmentService: AppointmentService, private userService: UserService  ) { }

  ngOnInit(): void {
    this.route.queryParams
    .subscribe(params => {
      this.id = params['id'];

      //otkomentarisati kada bude login
     // this.appointmentService.getAppointmentByID(this.id).subscribe(res => {
      this.appointmentService.getAppointmentByID(2).subscribe(res => {
        this.appointment = res;        
      })

    } );

  }

  public statusToString(status:boolean){
    if (status == true){
        return "Yes";
    }else{
        return "No";
    }
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

public examination() {
  this.proceedEx=true;
}

public examinationEnd() {
  this.appointmentService.updateAppointment(this.survey, this.id).subscribe(res =>{
    alert("The patient examination is complete.")  
    });

}

public cancel(){
  alert("Examination was canceled due to questionnaires.");
}


public cancelWithStriks() {
  this.userService.updateUserS(this.appointment.userID).subscribe(res =>{
    alert("Examination was canceled because the patient did not show up (+1 negative point).");
    });  
}

}
