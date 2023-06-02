import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { AppointmentService } from '../services/appointment.service';


@Component({
  selector: 'app-penals',
  templateUrl: './penals.component.html',
  styleUrls: ['./penals.component.css']
})
export class PenalsComponent implements OnInit {
  penals: any[] = [];
  totalPenals: number = 0;

  constructor(private appointmenstService: AppointmentService) {}

  ngOnInit(): void {
    this.loadPenalsData();
  }

  loadPenalsData(): void {
    this.appointmenstService.getPenalsNumber().subscribe((data: any[]) => {
      this.penals = data;
      this.calculateTotalPenals();
    });
  }

  calculateTotalPenals(): void {
    this.totalPenals = this.penals.reduce((total, penal) => total + penal.penalNum, 0);
  }
}
