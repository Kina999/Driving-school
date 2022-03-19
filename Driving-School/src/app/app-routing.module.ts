import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './front-page/login/login.component';
import { CandidatPageComponent } from './candidat-page/candidat-page.component';
import { RegistrationComponent } from './front-page/registration/registration.component';
import { CandidateAccountComponent } from './candidat-page/candidate-account/candidate-account.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AdminAccountComponent } from './admin-page/admin-account/admin-account.component';

const routes: Routes = [
  {path: "", component: FrontPageComponent},
  {path: "login", component: LoginComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "candidat-page", component: CandidatPageComponent},
  {path: "candidate-account", component: CandidateAccountComponent},
  {path: "admin-page", component: AdminPageComponent},
  {path: "admin-account", component: AdminAccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
