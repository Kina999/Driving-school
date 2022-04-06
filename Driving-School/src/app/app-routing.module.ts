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
import { CandidatInstructorComponent } from './candidat-page/candidat-instructor/candidat-instructor.component';
import { InstructorRequestsComponent } from './instructor-page/instructor-requests/instructor-requests.component';
import { InstructorCandidatesComponent } from './instructor-page/instructor-candidates/instructor-candidates.component';
import { InstructorCalendarComponent } from './instructor-page/instructor-calendar/instructor-calendar.component';
import { CandidateTerminsComponent } from './candidat-page/candidate-termins/candidate-termins.component';
import { AdminCandidatesComponent } from './admin-page/admin-candidates/admin-candidates.component';
import {AdminTestsComponent} from './admin-page/admin-tests/admin-tests.component'

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
  {path: "instructor-licence", component: InstructorLicenceComponent},
  {path: "candidat-instructor", component: CandidatInstructorComponent},
  {path: "instructor-requests", component: InstructorRequestsComponent},
  {path: "instructor-candidates", component: InstructorCandidatesComponent},
  {path: "instructor-calendar", component: InstructorCalendarComponent},
  {path: "candidate-termins", component: CandidateTerminsComponent},
  {path: "admin-candidates", component: AdminCandidatesComponent},
  {path: "admin-tests", component: AdminTestsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
