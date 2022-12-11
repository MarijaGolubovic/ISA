import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AppointmentStatus, CreateAppointmentDTO } from '../model/appointment.model';
import { QUestionnaireRespons } from '../model/questionnaire.model';
import { AppointmentService } from '../services/appointment.service';
import { QuestionnaireService } from '../services/questionnaire.service';


@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent{

  appointment: CreateAppointmentDTO = new CreateAppointmentDTO
  ();

  constructor(private questionnaireService: QuestionnaireService, private router: Router,
                 private route: ActivatedRoute, private appointmentService: AppointmentService, private alert: NgToastService) { }

  public questionnaire=new QUestionnaireRespons();

  questionnaireForm= new FormGroup({
    question1: new FormControl(null,[Validators.required]),
    question2: new FormControl(null,[Validators.required]),
    question3: new FormControl(null,[Validators.required]),
    question4: new FormControl(null,[Validators.required]),
    question5: new FormControl(null,[Validators.required]),
    question6: new FormControl(null,[Validators.required]),
    question7: new FormControl(null,[Validators.required]),
    question8: new FormControl(null,[Validators.required]),
    question9: new FormControl(null,[Validators.required]),
    question10: new FormControl(null,[Validators.required]),
    question11: new FormControl(null,[Validators.required]),
    question12: new FormControl(null,[Validators.required]),
  });

  
  get question1()  {
    return this.questionnaireForm.get('question1'); 
  }

  get question2()  {
    return this.questionnaireForm.get('question2'); 
  }

  get question3()  {
    return this.questionnaireForm.get('question3'); 
  }

  get question4()  {
    return this.questionnaireForm.get('question4'); 
  }

  get question5()  {
    return this.questionnaireForm.get('question5'); 
  }

  get question6()  {
    return this.questionnaireForm.get('question6'); 
  }

  get question7()  {
    return this.questionnaireForm.get('question7'); 
  }

  get question8()  {
    return this.questionnaireForm.get('question8'); 
  }

  get question9()  {
    return this.questionnaireForm.get('question9'); 
  }

  get question10()  {
    return this.questionnaireForm.get('question10'); 
  }

  get question11()  {
    return this.questionnaireForm.get('question11'); 
  }

  get question12()  {
    return this.questionnaireForm.get('question12'); 
  }

  async redirectToAutorizedUsers() : Promise<void>{
    await this.router.navigateByUrl('/autorizedUser')
  }

  async redirectToScheduleAppointment() : Promise<void>{
    await this.router.navigateByUrl('/appointment/scheduleNew')
  }

  public saveQuestionaire(){
    this.route.queryParams
      .subscribe(params => {
        this.appointment.bloodBankID = params['centerid'];
        this.appointment.date = new Date(params['date']);
        this.appointment.duration = 15;
        this.appointment.status = AppointmentStatus.BUSY;
        this.appointment.time = params['time'];
      }
    );
      
      
      this.appointmentService.scheduleAppointment(this.appointment).subscribe(res =>{
        alert("succesful")
        this.alert.success({detail: 'Success!', summary: "You are successfully schedule appointment!", duration: 5000});
      });
      this.questionnaireService.saveQuestionaire(this.questionnaire).subscribe(res => {
        //this.redirectToScheduleAppointment();
        //return console.log("Questinaire is save!");
      });
  }


  

}
