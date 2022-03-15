import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './front-page/login/login.component';
import { RegistrationComponent } from './front-page/registration/registration.component';

const routes: Routes = [
  {path: "", component: FrontPageComponent},
  {path: "login", component: LoginComponent},
  {path: "registration", component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
