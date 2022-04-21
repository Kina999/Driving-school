import { NgModule } from '@angular/core';
import { DatePipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './front-page/login/login.component';
import { RegistrationComponent } from './front-page/registration/registration.component';
import { CandidatPageComponent } from './candidat-page/candidat-page.component';
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
import { AgmCoreModule } from '@agm/core';
import { AdminCandidatesComponent } from './admin-page/admin-candidates/admin-candidates.component';
import { AdminTestsComponent } from './admin-page/admin-tests/admin-tests.component';
import { CandidateTestsComponent } from './candidat-page/candidate-tests/candidate-tests.component';
import { InstructorHistoryComponent } from './instructor-page/instructor-history/instructor-history.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    LoginComponent,
    RegistrationComponent,
    CandidatPageComponent,
    CandidateAccountComponent,
    AdminPageComponent,
    InstructorsComponent,
    InstructorPageComponent,
    InstructorAccountComponent,
    InstructorLicenceComponent,
    CandidatInstructorComponent,
    InstructorRequestsComponent,
    InstructorCandidatesComponent,
    InstructorCalendarComponent,
    CandidateTerminsComponent,
    AdminCandidatesComponent,
    AdminTestsComponent,
    CandidateTestsComponent,
    InstructorHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBZDx6ISSPAxZfodnJrb5AhMD5omR9CcWg',
      libraries: ['places']
    }),
  ],
  providers: [
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
