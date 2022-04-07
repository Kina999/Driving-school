import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = '';
  password: string = '';

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }
  
  loginPage(){
    this.router.navigate(['login']);
  }
  register(){
    this.router.navigate(['registration']);
  }
  login(){
    var body = {email: this.email,
      password: this.password};
    this.http.post('http://localhost:8080/authentication/login', body)
    .subscribe((data: any) => {
                        if(data == null){alert("Incorrect credentials")}
                        if(data.role === 'CANDIDATE'){
                          localStorage.setItem('currentUser', JSON.stringify(data));
                          this.router.navigate(['candidat-page']);
                        }else if(data.role === 'ADMINISTRATOR'){
                          localStorage.setItem('currentUser', JSON.stringify(data));
                          this.router.navigate(['instructors']);
                        }else if(data.role === 'INSTRUCTOR'){
                          localStorage.setItem('currentUser', JSON.stringify(data));
                          this.router.navigate(['instructor-candidates']);
                        }else{alert("Incorrect credentials")}});

  }
}
