import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-center-predefined-appointments',
  template: '<ejs-schedule></ejs-schedule>',
  //templateUrl: './center-predefined-appointments.component.html',
  styleUrls: ['./center-predefined-appointments.component.css']
})
export class CenterPredefinedAppointmentsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
