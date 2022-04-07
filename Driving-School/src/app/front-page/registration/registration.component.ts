import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  email: string = '';
  password: string = '';
  reenteredPassword: string = '';
  name: string = '';
  lastName: string = '';
  phoneNumber: string = '';

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }

  login(){
    this.router.navigate(['login']);
  }
  
  registrationPage(){
    this.router.navigate(['registration']);
  }
  
  register(){
    if(this.password != this.reenteredPassword){
      alert("Passwords not matching");
    }else{
      var body = {email: this.email,
                  password: this.password,
                  name: this.name,
                  lastname: this.lastName,
                  phoneNumber: this.phoneNumber};
      this.http.post('http://localhost:8080/candidates/registration', body)
          .subscribe(data => {if(data){
            this.login();
            alert("Sucessful registration, now you can login!")}else{alert("Username already exists")}});
      
    }
  }

}
