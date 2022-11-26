import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent{

  myForm = new FormGroup({
    first: new FormControl(),
    last: new FormControl()
  });

  constructor(private  fb: FormBuilder) {
    
  }

  

  scheduleAppointment(){

  }
}
