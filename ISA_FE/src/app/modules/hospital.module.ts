import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { MaterialModule } from "src/app/material/material.module";
import { UsersComponent } from "./users/users.component";
import { EditUserComponent } from './edit-user/edit-user.component';
import { SendNewsToHospitalComponent } from './send-news-to-hospital/send-news-to-hospital.component';


const routes: Routes = [
  { path: 'users', component: UsersComponent },
  { path: 'users/edit', component: EditUserComponent },
  { path: 'hospital/sendNews', component: SendNewsToHospitalComponent }
];

@NgModule({
  declarations: [
    UsersComponent,
    EditUserComponent,
    SendNewsToHospitalComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ],
  exports: [ RouterModule ]
})
export class HospitalModule { }