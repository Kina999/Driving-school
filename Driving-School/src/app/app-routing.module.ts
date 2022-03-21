import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './front-page/login/login.component';
import { CandidatPageComponent } from './candidat-page/candidat-page.component';
import { RegistrationComponent } from './front-page/registration/registration.component';
import { CandidateAccountComponent } from './candidat-page/candidate-account/candidate-account.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { InstructorsComponent } from './admin-page/instructors/instructors.component';
import { InstructorPageComponent } from './instructor-page/instructor-page.component';
import { InstructorAccountComponent } from './instructor-page/instructor-account/instructor-account.component';
import { InstructorLicenceComponent } from './instructor-page/instructor-licence/instructor-licence.component';

const routes: Routes = [
  {path: "", component: FrontPageComponent},
  {path: "login", component: LoginComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "candidat-page", component: CandidatPageComponent},
  {path: "candidate-account", component: CandidateAccountComponent},
  {path: "admin-page", component: AdminPageComponent},
  {path: "instructors", component: InstructorsComponent},
  {path: "instructor-page", component: InstructorPageComponent},
  {path: "instructor-account", component: InstructorAccountComponent},
  {path: "instructor-licence", component: InstructorLicenceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
