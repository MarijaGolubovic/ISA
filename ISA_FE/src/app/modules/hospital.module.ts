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
import { EditUserComponent } from './edit-user/edit-user.component';
import { AuthorizedUserComponent } from './authorized-user/authorized-user.component';
import { CentersComponent } from './centers/centers.component';
import { EditBloodBankComponent } from "./bloodBank/bloodBank.component";
import { ChangeUserPasswordComponent } from './change-user-password/change-user-password.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { QuestionnaireComponent } from './questionnaire/questionnaire.component';

const routes: Routes = [
  { path: 'users', component: UsersComponent },
  { path: 'users/edit', component: EditUserComponent },
  { path: 'autorizedUser', component: AuthorizedUserComponent },
  { path: 'autorizedUser/centers', component: CentersComponent },
  { path: 'register-bloodbank', component: BloodbankRegistrationComponent},
  { path: 'autorizedUser/center', component: EditBloodBankComponent },
  { path: 'users/changePassword', component: ChangeUserPasswordComponent },
  { path: 'register-bloodbank', component: BloodbankRegistrationComponent},
  { path: 'userRegistration', component: UserRegistrationComponent },
  { path: 'questionnaire', component: QuestionnaireComponent }
];

@NgModule({
  declarations: [
    UsersComponent,
    BloodbankRegistrationComponent,
    EditUserComponent,
    AuthorizedUserComponent,
    CentersComponent,
    EditBloodBankComponent,
    ChangeUserPasswordComponent,
    UserRegistrationComponent,
    QuestionnaireComponent
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