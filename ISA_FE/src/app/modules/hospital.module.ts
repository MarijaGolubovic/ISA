import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { MaterialModule } from "src/app/material/material.module";
import { UsersComponent } from "./users/users.component";
import { EditUserComponent } from './edit-user/edit-user.component';
import { AuthorizedUserComponent } from './authorized-user/authorized-user.component';
import { CentersComponent } from './centers/centers.component';
import { EditBloodBankComponent } from "./bloodBank/bloodBank.component";
import { ChangeUserPasswordComponent } from './change-user-password/change-user-password.component';




const routes: Routes = [
  { path: 'users', component: UsersComponent },
  { path: 'users/edit', component: EditUserComponent },
  { path: 'autorizedUser', component: AuthorizedUserComponent },
  { path: 'autorizedUser/centers', component: CentersComponent },
  { path: 'autorizedUser/center', component: EditBloodBankComponent },
  { path: 'users/changePassword', component: ChangeUserPasswordComponent }
];

@NgModule({
  declarations: [
    UsersComponent,
    EditUserComponent,
    AuthorizedUserComponent,
    CentersComponent,
    EditBloodBankComponent,
    ChangeUserPasswordComponent
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