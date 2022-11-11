import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { MaterialModule } from "src/app/material/material.module";
import { UsersComponent } from "./users/users.component";
import { EditUserComponent } from './edit-user/edit-user.component';
import { AuthorizedUserComponent } from './authorized-user/authorized-user.component';
import { CentersComponent } from './centers/centers.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';


const routes: Routes = [
  { path: 'users', component: UsersComponent },
  { path: 'users/edit', component: EditUserComponent },
  { path: 'autorizedUser', component: AuthorizedUserComponent },
  { path: 'autorizedUser/centers', component: CentersComponent },
  { path: 'userRegistration', component: UserRegistrationComponent },

];

@NgModule({
  declarations: [
    UsersComponent,
    EditUserComponent,
    AuthorizedUserComponent,
    CentersComponent,
    UserRegistrationComponent,
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