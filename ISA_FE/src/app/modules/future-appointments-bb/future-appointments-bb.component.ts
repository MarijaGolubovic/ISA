import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { FutureAppointmentBBDTO, FutureAppointmentDTO } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';

@Component({
  selector: 'app-future-appointments-bb',
  templateUrl: './future-appointments-bb.component.html',
  styleUrls: ['./future-appointments-bb.component.css']
})
export class FutureAppointmentsBBComponent implements OnInit {
  public dataSource = new MatTableDataSource<FutureAppointmentBBDTO>();
  public displayedColumns = ['name', 'Date', 'Time', 'StartApp'];

  constructor(private appService: AppointmentService, private router: Router) { }

  ngOnInit(): void {
    this.appService.getFutureAppointmentsBB().subscribe(res => {
      this.dataSource.data = res;
    })
  }

  async startApp(id: number){
    await this.router.navigateByUrl('/startAppointment?id=' + id )
  }

}
