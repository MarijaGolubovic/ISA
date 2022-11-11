import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { MaterialModule } from "src/app/material/material.module";
import { UsersComponent } from "./users/users.component";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BloodbankRegistrationComponent } from "./register-bloodbank/register-bloodbank.component";
import { BrowserModule } from "@angular/platform-browser";
import { MatSelectModule } from "@angular/material/select";

const routes: Routes = [
  { path: 'users', component: UsersComponent },
  { path: 'register-bloodbank', component: BloodbankRegistrationComponent},
];

@NgModule({
  declarations: [
    UsersComponent,
    BloodbankRegistrationComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    BrowserModule,
    MatSelectModule,
    RouterModule.forChild(routes)
  ],
  exports: [ RouterModule ]
})
export class HospitalModule { }