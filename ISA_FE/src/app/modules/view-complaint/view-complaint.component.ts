import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import {NgToastService} from "ng-angular-popup";
import {DialogPosition, MatDialog} from '@angular/material/dialog';
import { AddCommentService } from '../services/add-comment.service';
import { ComplaintService } from '../services/complaint.service';
import { Complaint, ComplaintStatus } from '../model/complaint';
import { AddCommentComponent } from './add-comment.component/add-comments.component';

@Component({
  selector: 'app-view-complaint',
  templateUrl: './view-complaint.component.html',
  styleUrls: ['./view-complaint.component.css']
})
export class ViewComplaintComponent implements OnInit{

  public complaint: Complaint = new Complaint();
  public comment : string = "";
  public allRequests : Complaint[] = [];
  public dataSource = new MatTableDataSource<Complaint>();
  public pendingRequests : Complaint[] = [];
  public pendingDataSource = new MatTableDataSource<Complaint>();
  public displayedColumns = ['date','text', 'status','user', 'bloodbank','reply'];
  public displayedColumns1 = ['date','text', 'status','user', 'bloodbank','reply'];


  constructor(private commentService: AddCommentService,private complaintService: ComplaintService, private router: Router, private alert: NgToastService,public dialog: MatDialog) { }

  ngOnInit(): void {
    this.complaintService.getAllComplaints().subscribe(res => {
      this.allRequests = res;
      this.dataSource.data = this.allRequests;
    })
    this.complaintService.getAllComplaintsOnPending().subscribe(res => {
      this.pendingRequests = res;
      this.pendingDataSource.data = this.pendingRequests;
    })
  }

    openDialog(complaint:Complaint): void {

      const dialogRef = this.dialog.open(AddCommentComponent, {
        width: '400px',
        position: {left:'600px', top: '0px'},
        data: {comment: this.comment},
      });

      dialogRef.afterClosed().subscribe(result => {
        this.comment = result;
        if (this.comment == "" || this.comment == undefined){

        }else{
          complaint.reply = this.comment;
          complaint.status = ComplaintStatus.REPLIED;
          this.commentService.replyOnComplaint(complaint).subscribe(_ => {
            this.ngOnInit();
          });
        }
      });
    }

}
