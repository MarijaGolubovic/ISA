import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../services/appointment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-appointments',
  templateUrl: './view-appointments.component.html',
  styleUrls: ['./view-appointments.component.css']
})
export class ViewAppointmentsComponent implements OnInit {

  appointments: any[] = [];

  constructor(private appointmentService: AppointmentService,  private router: Router) { }

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments() {
    this.appointmentService.getAllFreeAppointments().subscribe(
      (data: any[]) => {
        this.appointments = data;
      },
      error => {
        console.error('Greška pri dohvatanju termina:', error);
      }
    );
  }


  takeAppointment(appointmentId: number) {
    this.appointmentService.takeAppointment(appointmentId).subscribe(
      (response: any) => {
        if (response === 0) {
          console.log('Pitanje - Preusmeravanje na home.');
          this.router.navigate(['/usersFutureAppointments']);
        
        } else if(response === 1) {
          alert("You need to fill out a questionnaire!")
          this.router.navigate(['/questionnaire']);
        }else if(response === 2) {
          alert("You have more than three penalties this month!")
          
        }else if(response === 3) {
          alert("You have already donated blood in the past 6 months!")
          this.router.navigate(['/usersFutureAppointments']);
        }else if(response === 4) {
          alert("You already have an appointment booked!")
          this.router.navigate(['/usersFutureAppointments']);
        }

      },
      error => {
        console.error('Greška pri zakazivanju termina:', error);
      }
    );
  }
  
  
  

}
