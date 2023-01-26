import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { MaterialModule } from "src/app/material/material.module";
import { UsersComponent } from "./users/users.component";
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDatepickerModule} from "@angular/material/datepicker";
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
import { SendNewsToHospitalComponent } from './send-news-to-hospital/send-news-to-hospital.component';
import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { SchedulingNewAppointmentComponent } from "./scheduling-new-appointment/scheduling-new-appointment.component";
import { FutureAppointmentsComponent } from './future-appointments/future-appointments.component';
import { ScheduleModule, RecurrenceEditorModule, DayService, WeekService, WorkWeekService, MonthService, MonthAgendaService} from '@syncfusion/ej2-angular-schedule';
import { CenterPredefinedAppointmentsComponent } from './center-predefined-appointments/center-predefined-appointments.component';
import { NgToastModule } from 'ng-angular-popup';
import { MatTabsModule } from "@angular/material/tabs";
import { AddAdminCenterComponent } from "./add-admin-center/add-admin-center.component";
import { ChangeAdminSistemPasswordComponent } from "./change-admin-sistem-password/change-admin-sistem-password.component";
import { AddCommentComponent } from "./view-complaint/add-comment.component/add-comments.component";
import { ViewComplaintComponent } from "./view-complaint/view-complaint.component";
import { ViewAllBloodSubscriptionsComponent } from './view-all-blood-subscriptions/view-all-blood-subscriptions.component';
import { StartAppointmentComponent } from './start-appointment/start-appointment.component';
import { AllCentersComponent } from './all-centers/all-centers.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { UsersBloodCenterComponent } from './users-blood-center/users-blood-center.component';
import { FutureAppointmentsBBComponent } from './future-appointments-bb/future-appointments-bb.component';
import { BloodSupplyComponent } from './blood-supply/blood-supply.component';


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
  { path: 'questionnaire', component: QuestionnaireComponent },
  { path: 'hospital/sendNews', component: SendNewsToHospitalComponent },
  { path: 'appointment/create', component: CreateAppointmentComponent },
  { path: 'appointment/scheduleNew', component: SchedulingNewAppointmentComponent },
  { path: 'usersFutureAppointments', component: FutureAppointmentsComponent },
  { path: 'centerPredefinedAppointments', component: FutureAppointmentsComponent },
  { path: 'complaints', component: ViewComplaintComponent},
  { path: 'complaints/reply', component: AddCommentComponent},
  { path: 'add-admin-centar', component: AddAdminCenterComponent},
  { path: 'admin-sistem/changePassword', component : ChangeAdminSistemPasswordComponent},
  { path: 'view-all-blood-subscriptions', component : ViewAllBloodSubscriptionsComponent},
  { path: 'startAppointment', component : StartAppointmentComponent},
  { path: '', component : AllCentersComponent},
  { path: 'login', component : LoginUserComponent},
  { path: 'homeAdmin', component : UsersBloodCenterComponent},
  { path: 'appointmentBB', component : FutureAppointmentsBBComponent},
  { path: 'bloodSupply', component : BloodSupplyComponent}
]

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
    QuestionnaireComponent,
    SendNewsToHospitalComponent,
    CreateAppointmentComponent,
    SchedulingNewAppointmentComponent,
    FutureAppointmentsComponent,
    CenterPredefinedAppointmentsComponent,
    ViewComplaintComponent,
    AddCommentComponent,
    AddAdminCenterComponent,
    ChangeAdminSistemPasswordComponent,
    ViewAllBloodSubscriptionsComponent,
    StartAppointmentComponent,
    AllCentersComponent,
    LoginUserComponent,
    UsersBloodCenterComponent,
    FutureAppointmentsBBComponent,
    BloodSupplyComponent
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
    MatDatepickerModule,
    RouterModule.forChild(routes),
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgToastModule,
    ScheduleModule, RecurrenceEditorModule,
    MatTabsModule
    ],
  providers: [DayService, WeekService, WorkWeekService, MonthService, MonthAgendaService],
  exports: [ RouterModule ]
})
export class HospitalModule { }