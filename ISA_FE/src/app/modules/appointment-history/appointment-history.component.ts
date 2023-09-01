// appointments-history.component.ts
import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../services/appointment.service';


@Component({
  selector: 'app-appointment-history',
  templateUrl: './appointment-history.component.html',
  styleUrls: ['./appointment-history.component.css']
})
export class AppointmentHistoryComponent implements OnInit {
  appointments: any[] = [];
  sortedColumn: string = '';

  constructor(private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments() {
    this.appointmentService.getAppointmentHistoryForUser().subscribe(
      (data: any[]) => {
        this.appointments = data;
      },
      error => {
        console.error('Greška pri dohvatanju termina:', error);
      }
    );
  }

  sortData(key: string) {
    if (key === this.sortedColumn) {
      this.appointments.reverse();
    } else {
      this.appointments.sort((a, b) => {
        if (key === 'status') {
          return a[key].localeCompare(b[key]);
        } else if (key === 'duration') {
          return a[key] - b[key];
        } else if (key === 'date') {
          return new Date(a[key]).getTime() - new Date(b[key]).getTime();
        } else if (key === 'bloodBank.name') { 
          return a.bloodBank.name.localeCompare(b.bloodBank.name);
        } else {
          return a[key].toString().localeCompare(b[key].toString());
        }
      });
      this.sortedColumn = key;
    }
  }
  

  getStatusLabel(status: string): string {
    switch (status) {
      case 'FREE':
        return 'FREE';
      case 'BUSY':
        return 'BUSY';
      case 'CANCELD':
        return 'CANCELD';
      case 'DONE':
        return 'DONE';
      default:
        return '';
    }
  }
}
