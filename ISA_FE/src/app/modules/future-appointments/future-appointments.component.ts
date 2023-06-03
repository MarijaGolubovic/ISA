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

  appointments: any[] = [];

  constructor(private appService: AppointmentService, private router: Router) { }

  ngOnInit(): void {
    this.loadAppointments();
  }


  loadAppointments() {
    this.appService.getFutureAppointments().subscribe(
      (data: any[]) => {
        this.appointments = data;
      },
      error => {
        console.error('Greška pri dohvatanju termina:', error);
      }
    );
  }


  cancelAppointment(appointmentId: number) {
    
    this.appService.cancelAppointment(appointmentId).subscribe(
      (response: boolean) => {
        if (response) {
          console.log('Termin uspješno otkazan.');
          this.router.navigate(['appointmentsHistory']); 
          this.loadAppointments();
        } else {
          const twentyFourHoursNotice = 'Appointment can be canceled at least 24 hours before the start.';
          alert(twentyFourHoursNotice);
        }
      },
      error => {
        console.error('Greška pri otkazivanju termina:', error);
        this.loadAppointments();
      }
    );
  
}

}
