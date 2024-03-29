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
import {CalendarModule, CalendarMonthModule, CalendarWeekModule, DateAdapter} from "angular-calendar";
import {adapterFactory} from "angular-calendar/date-adapters/date-fns";
import { CalendarComponent } from "./calendar/calendar.component";
import { UsersBloodCenterComponent } from './users-blood-center/users-blood-center.component';
import { FutureAppointmentsBBComponent } from './future-appointments-bb/future-appointments-bb.component';
import { BloodSupplyComponent } from './blood-supply/blood-supply.component';
import { UserTypeGuard } from "../auth/auth_guards";
import { UserRolesGuards } from "../auth/user_guard";
import { UnautorizedComponentComponent } from './unautorized-component/unautorized-component.component';
import { ViewAppointmentsComponent } from './view-appointments/view-appointments.component';
import { AppointmentHistoryComponent } from './appointment-history/appointment-history.component';
import { QrCodesComponent } from './qr-codes/qr-codes.component';
import { PenalsComponent } from './penals/penals.component';
import { VahiclesCoordinatesComponent } from "./vahicles-coordinates/vahicles-coordinates.component";



const routes: Routes = [
  { path: 'users', component: UsersComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: 'users/edit', component: EditUserComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'autorizedUser', component: AuthorizedUserComponent },
  { path: 'autorizedUser/centers', component: CentersComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'register-bloodbank', component: BloodbankRegistrationComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_SISTEM } },
  { path: 'autorizedUser/center', component: EditBloodBankComponent },
  { path: 'users/changePassword', component: ChangeUserPasswordComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'register-bloodbank', component: BloodbankRegistrationComponent},
  { path: 'userRegistration', component: UserRegistrationComponent },
  { path: 'questionnaire', component: QuestionnaireComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'hospital/sendNews', component: SendNewsToHospitalComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: 'appointment/create', component: CreateAppointmentComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: 'appointment/scheduleNew', component: SchedulingNewAppointmentComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'usersFutureAppointments', component: FutureAppointmentsComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'centerPredefinedAppointments', component: FutureAppointmentsComponent , canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: 'complaints', component: ViewComplaintComponent},
  { path: 'complaints/reply', component: AddCommentComponent},
  { path: 'add-admin-centar', component: AddAdminCenterComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_SISTEM } },
  { path: 'admin-sistem/changePassword', component : ChangeAdminSistemPasswordComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_SISTEM } },
  { path: 'view-all-blood-subscriptions', component : ViewAllBloodSubscriptionsComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: 'startAppointment', component : StartAppointmentComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: '', component : LoginUserComponent},
  { path: 'allCenters', component : AllCentersComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'login', component : LoginUserComponent},
  { path: 'calendar', component : CalendarComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  // { path: 'homeAdmin', component : UsersBloodCenterComponent},
  { path: 'homeAdmin', component: UsersBloodCenterComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } },
  { path: 'appointmentBB', component : FutureAppointmentsBBComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_SISTEM } },
  { path: 'bloodSupply', component : BloodSupplyComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_SISTEM } },
  { path: 'unautorized', component : UnautorizedComponentComponent},
  { path: 'viewAppointments', component : ViewAppointmentsComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'appointmentsHistory', component : AppointmentHistoryComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'qrCodes', component : QrCodesComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'penals', component : PenalsComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_REGISTERED } },
  { path: 'map', component : VahiclesCoordinatesComponent, canActivate: [UserTypeGuard], data: { requiredRole: UserRolesGuards.ROLE_ADMIN_CENTER } }
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
    CalendarComponent,
    UsersBloodCenterComponent,
    FutureAppointmentsBBComponent,
    BloodSupplyComponent,
    UnautorizedComponentComponent,
    ViewAppointmentsComponent,
    AppointmentHistoryComponent,
    QrCodesComponent,
    PenalsComponent
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
    MatTabsModule,
    CalendarWeekModule,
    CalendarMonthModule,
  CalendarModule.forRoot({
    provide: DateAdapter,
    useFactory: adapterFactory,
  }),
    ],
  providers: [DayService, WeekService, WorkWeekService, MonthService, MonthAgendaService, UserTypeGuard],
  exports: [ RouterModule ]
})
export class HospitalModule { }