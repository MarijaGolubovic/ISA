import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ViewComplaintComponent } from '../view-complaint.component';


export interface DialogData {
  comment: string;
}

@Component({
  selector: 'add-comment',
  templateUrl: './add-comments.component.html',
  styleUrls: ['./add-comments.component.css']
})
export class AddCommentComponent{

  
  comment : string = "";

  constructor( private router: Router, public dialogRef: MatDialogRef<ViewComplaintComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,) {}

  public close(){
    this.dialogRef.close("");
  }    

  public addComment() {
    if (!this.isValidInput()){
      alert("Comment cannot be empty.");
      return;
    }
    try {
      this.dialogRef.close(this.comment);
    }catch(error) {
      alert(error)
    }
}

  private isValidInput(): boolean {
    return this.comment != '';
  }

}
