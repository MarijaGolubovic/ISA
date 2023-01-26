import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import { EventColor } from 'calendar-utils';
import {addDays, subDays} from 'date-fns';
import {Router} from "@angular/router";
import {CalendarEvent, CalendarView} from "angular-calendar";
import {map} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import * as moment from "moment/moment";
import { AppointmentResponse } from '../model/appointment.model';
import { DataServiceService } from '../services/data-service.service';
import { AppointmentService } from '../services/appointment.service';

const colors: Record<string, EventColor> = {
    red: {
      primary: '#ad2121',
      secondary: '#FAE3E3',
    },
    blue: {
      primary: '#ffffff',
      secondary: '#ffffff',
    },
    green: {
      primary: "#e3bc08",
      secondary: "rgba(205,227,8,0.71)",
      secondaryText:'#263238'
    },
    orange : {
      primary: '#ffffff',
      secondary: "rgba(205,227,8,0.71)",
      secondaryText:'#263238'
    },
    pink : {
      primary: '#1ECBE1',
      secondary: 'rgba(42,155,203,0.62)',
      secondaryText:'#263238'
    },
    selected : {
      primary: '#ad2121',
      secondary: '#c3796f',
      secondaryText:'#263238'
    }
  };
  @Component({
    selector: 'app-calendar',
    templateUrl: './calendar.component.html',
    styleUrls: ['./calendar.component.css']
  })
  export class CalendarComponent implements OnInit {
    viewDate: Date;
    consiliumsCalender: CalendarEvent<{}>[] = [];
    appointmentCalender: CalendarEvent<{}>[] = [];
    events : CalendarEvent<{}>[]= [];
    dayStartHour = 8;
    dayEndHour = 22;
    hourSegmentHeight = 110;
    daysInWeek = 7;
    view: CalendarView = CalendarView.Week;
    viewDateEnd: Date;
    canClickMoreDetails:boolean = false
    monthView: boolean = false
    viewButton:string = "Month view"
  
    selectedEventApp: CalendarEvent<{ appointment: AppointmentResponse }> = {
      title: null as any,
      start: null as any,
      color: { ...colors['blue'] },
      end: null as any,
      meta: null as any,
    };

    constructor(private readonly appointmentService: AppointmentService,private dataService: DataServiceService,
                private readonly router: Router, private dialog : MatDialog) {
      this.viewDate = new Date();
      this.viewDateEnd = addDays(this.viewDate, 6);
      this.dataService.getData().subscribe(data => {
        // @ts-ignore
        let apps = this.appointmentCalender.filter(e => {
           // @ts-ignore
           if(e.meta.appointment.id != data){
             return true
           }
        });
        this.events =  [...this.consiliumsCalender, ...apps];
      });
    }
                
    ngOnInit(): void {
      this.getAppointmentsForDoctor()
    }
  
    private getAppointmentsForDoctor() {
      this.appointmentService.getApointmetnResponsesForCalendar()
        .pipe(
          map((results: AppointmentResponse[]) => {
            return results.map((appointment: AppointmentResponse) => {
              return {
                title: this.createAppointmentClient(appointment),
                start: appointment.time,
                end: moment(appointment.time).add(30, 'm').toDate(),
                color: {...colors['pink']},
                meta: {
                  appointment,
                  id: 2
                }
              };
            });
          })
        )
        .subscribe(
          //@ts-ignore
          (response: CalendarEvent<{ appointment: AppointmentResponse }>[]) => {
            //@ts-ignore
            this.appointmentCalender = response
            this.createEvents();
            console.log(this.appointmentCalender)
            // @ts-ignore
            console.log(this.appointmentCalender[0].meta.appointment.id)
          }, (error: HttpErrorResponse) => {
            console.log(error.message);
          })
    }
  
    async handleCurrent(): Promise<void> {
      this.viewDate = new Date();
      this.viewDateEnd = addDays(this.viewDate, 6);
      await new Promise(resolve => setTimeout(resolve, 100));
    }
    async handleNext(): Promise<void> {
      this.viewDate = addDays(this.viewDate, 7);
      this.viewDateEnd = addDays(this.viewDate, 6);
      await new Promise(resolve => setTimeout(resolve, 100));
    }
    onEventClick(event: any): void {
      // console.log(event)
      // this.selectedEvent = event.event;
      // // @ts-ignore
      // if(this.selectedEvent.meta.id === 1)
      //   this.showDoctors()
      // // @ts-ignore
      // else {
      //   this.selectedEventApp = event.event
      //   console.log(this.selectedEventApp)
      //   this.showAppointmentDetails();
      // }
      // this.canClickMoreDetails = true
  
    }
    async handlePrevious(): Promise<void> {
      this.viewDate = subDays(this.viewDate, 7);
      this.viewDateEnd = addDays(this.viewDate, 6);
      await new Promise(resolve => setTimeout(resolve, 100));
    }
    newAppointment() {
      // this.dialog.open(OtherDoctorsPreviewComponent, {
      //   width: '600px',
      //    height:'500px',
      //    data: { consilium: this.selectedEvent.meta?.consilium }
      //  });
      // this.router.navigate(['create-schedule'])
    }

    monthShow() {
      this.monthView = !this.monthView;
      switch (this.viewButton) {
        case 'Month view':
          this.viewButton = 'Week view'
          break
        case 'Week view':
          this.viewButton = 'Month view'
          break
      }
    }
    openScheduleConsilium(): void {
      this.router.navigate(['schedule-consilium']);
    }
  
    private createAppointmentClient(appointment: AppointmentResponse):string {
      return (
        'Appointment'+ '\n'+
        'Start time: '+
        moment(appointment.time).format('h:mm A')+
        '\n' +
        'Finish time: '+
        moment(moment(appointment.time).add(30, 'm').toDate()).format('h:mm A')+
        '\n' +
        'Patient:'+
        appointment.userName  + ' ' + appointment.userSurname
      );
    }

    private addMinutes(date : Date, minutes : number) {
        date.setMinutes(date.getMinutes() + minutes);
      
        return date;
      }
  
    private createEvents() {
      this.events =  [...this.consiliumsCalender, ...this.appointmentCalender];
      console.log(this.events);
    }
  
    /*private showAppointmentDetails() {
      this.dialog.open(AppointmentDetailsDialogComponent, {
        width: '600px',
        height:'320px',
        data: { appointment: this.selectedEventApp.meta?.appointment }
      });
    }*/
  }
  