import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QUestionnaireRespons } from '../model/questionnaire.model';
import { QuestionnaireService } from '../services/questionnaire.service';


@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent{

  constructor(private questionnaireService: QuestionnaireService, private router: Router) { }

  public questionnaire=new QUestionnaireRespons();

  async redirectToAutorizedUsers() : Promise<void>{
    await this.router.navigateByUrl('/autorizedUser')
  }

  public saveQuestionaire(){
    this.questionnaireService.saveQuestionaire(this.questionnaire).subscribe(res => {
        this.redirectToAutorizedUsers();
        return console.log("Questinaire is save!");
    });
  }


  

}
