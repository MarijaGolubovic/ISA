import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Route, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AppointmentStatus, CreateAppointmentDTO } from '../model/appointment.model';
import { CenterResponse } from '../model/center.model';
import { DateTimeDTO } from '../model/datetime.model';
import { QUestionnaireRespons } from '../model/questionnaire.model';
import { AppointmentService } from '../services/appointment.service';
import { CenterService } from '../services/centers.service';
import { QuestionnaireService } from '../services/questionnaire.service';

@Component({
  selector: 'app-scheduling-new-appointment',
  templateUrl: './scheduling-new-appointment.component.html',
  styleUrls: ['./scheduling-new-appointment.component.css']
})
export class SchedulingNewAppointmentComponent implements OnInit {

  dateTime: DateTimeDTO = new DateTimeDTO();
  showCenters: boolean = false;

  public dataSource = new MatTableDataSource<CenterResponse>();
  public displayedColumns = ['name', 'city', 'street', 'streetNumber','grade','schedule'];
  public centers: CenterResponse[] = [];
  public searchedCenters: CenterResponse[] = [];
  public booleanValueByName: any = false;
  public booleanValueByCity: any = false;
  public booleanValueByStreet: any = false;
  public booleanValueByStreetNumber: any = false;
  public booleanValueByGrade: any = false;
  public searchName: string = '';
  public searchCity: string = '';
  public searchMinGrade: number = 0;
  public searchMaxGrade: number = 10;
  sortedData: CenterResponse[];

  appointment: CreateAppointmentDTO = new CreateAppointmentDTO();

  constructor(private centerService: CenterService, private questionnaireService: QuestionnaireService,
               private router: Router, private appointmentService: AppointmentService, private alert: NgToastService) {
    this.sortedData = this.centers.slice();
  }

  ngOnInit(): void {
    
  }

  async redirectToAutorizedUsers() : Promise<void>{
    await this.router.navigateByUrl('/autorizedUser')
  }

  async redirectToQuestionnaire(centerId: any) : Promise<void>{
    await this.router.navigateByUrl('/questionnaire?centerid=' + centerId  + '&' + 'date=' + this.dateTime.date + '&' + 'time=' + this.dateTime.startTime)
  }

  scheduleAppointment(centerId: any){
    this.questionnaireService.checkIfQuestionnaireHasBeenCompletedInLastSixMonths(1).subscribe(res=>{
      //otkomentarisi kad se uradi logovanje
      if(res === true){
        alert("You cannot schedule an appointment because you have donated blood in the last 6 months!")
        this.redirectToAutorizedUsers();
      }else{
        this.redirectToQuestionnaire(centerId);
      }
    })
      /*this.appointment.bloodBankID = centerId;
      this.appointment.date = this.dateTime.date;
      this.appointment.duration = 15;
      this.appointment.status = AppointmentStatus.BUSY;
      this.appointment.time = this.dateTime.startTime;
      this.appointmentService.scheduleAppointment(this.appointment).subscribe(res =>{
      this.alert.success({detail: 'Success!', summary: "You are successfully schedule appointment!", duration: 5000});
    })*/
  }

  getAllAvailableCenters(){
    (this.centerService.getAllAvailableCenters(this.dateTime)).subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
      this.sortedData = this.centers.slice();
      this.searchedCenters = res;
      if(this.centers.length != 0){
        this.showCenters = true;
      }else{
        alert("There are no free centers at the selected time!")
      }
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
    this.searchedCenters = []
    for(let center of this.centers){
      if(center.name.toLocaleLowerCase().includes(this.searchName.toLocaleLowerCase()) && center.city.toLocaleLowerCase().includes(this.searchCity.toLocaleLowerCase())){
        this.searchedCenters.push(center);
      }
    }
    this.dataSource.data = this.searchedCenters;
  }

  public filterCenters(){
    let filteredCenters: CenterResponse[] = [];
    this.dataSource.data = this.searchedCenters;
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
