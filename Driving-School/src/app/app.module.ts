import { NgModule } from '@angular/core';
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
import { AdminAccountComponent } from './admin-page/admin-account/admin-account.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    LoginComponent,
    RegistrationComponent,
    CandidatPageComponent,
    CandidateAccountComponent,
    AdminPageComponent,
    AdminAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
