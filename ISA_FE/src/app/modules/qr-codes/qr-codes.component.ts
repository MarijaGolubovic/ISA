import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppointmentService } from '../services/appointment.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-qr-codes',
  templateUrl: './qr-codes.component.html',
  styleUrls: ['./qr-codes.component.css']
})
export class QrCodesComponent implements OnInit {
  qrCodes: any[] = [];
  filteredQrCodes: any[] = [];
  statusOptions: string[] = ['FREE', 'BUSY', 'CANCELD', 'DONE'];
  selectedStatuses: string[] = []; 
  isDateAscending: boolean = true; 

  constructor(private http: HttpClient, private appointmentService: AppointmentService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.loadQRCodes();
  }

  loadQRCodes(): void {
    this.appointmentService.loadQRCodes().subscribe((data: any[]) => {
      this.qrCodes = data;
      this.applyFiltersAndSort();
    });
  }

  applyFiltersAndSort(): void {
    this.applyStatusFilter();
    this.sortByDate();
  }

  applyStatusFilter(): void {
    if (this.selectedStatuses.length === 0) {
      this.filteredQrCodes = this.qrCodes;
    } else {
      this.filteredQrCodes = this.qrCodes.filter(qrCode => this.selectedStatuses.includes(qrCode.appointmentStatus));
    }
  }

  sortByDate(): void {
    const multiplier = this.isDateAscending ? 1 : -1;
    this.filteredQrCodes.sort((a, b) => multiplier * (new Date(a.date).getTime() - new Date(b.date).getTime()));
  }

  sortBy(column: string): void {
    if (column === 'date') {
      this.isDateAscending = !this.isDateAscending;
      this.sortByDate();
    }
  }

  clearFilters(): void {
    this.selectedStatuses = [];
    this.applyFiltersAndSort();
  }

  getImageUrl(image: any): SafeUrl {
    const base64Image = 'data:image/png;base64,' + image;
    return this.sanitizer.bypassSecurityTrustUrl(base64Image);
  }
}
