import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

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
    var body = {name: 'kina'};
    this.http.get('http://localhost:8080/candidates/login')
        .subscribe(data => alert("Dsaas"));
  }
}
