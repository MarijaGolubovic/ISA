import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { FutureAppointmentDTO } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';

@Component({
  selector: 'app-future-appointments',
  templateUrl: './future-appointments.component.html',
  styleUrls: ['./future-appointments.component.css']
})
export class FutureAppointmentsComponent implements OnInit {

  public dataSource = new MatTableDataSource<FutureAppointmentDTO>();
  public displayedColumns = ['name', 'Date', 'Time'];

  constructor(private appService: AppointmentService, private router: Router) { }

  ngOnInit(): void {
    this.appService.getFutureAppointments().subscribe(res => {
      this.dataSource.data = res;
    })
  }

}
