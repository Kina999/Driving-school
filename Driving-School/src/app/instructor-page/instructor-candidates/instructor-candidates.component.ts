import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-instructor-candidates',
  templateUrl: './instructor-candidates.component.html',
  styleUrls: ['./instructor-candidates.component.css']
})
export class InstructorCandidatesComponent implements OnInit {
  instructorCandidates: any = [];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if(user != null){
      var userEmail = JSON.parse(user).email;
      this.http.get('http://localhost:8080/candidates/getInstructorCandidates?email=' + userEmail).subscribe(
        (data: any) => {
          if(data != null){
            this.instructorCandidates = data;
          }
      });
    }
  }

  logout(){
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  account(){
    this.router.navigate(['instructor-account']);
  }

  licence(){
    this.router.navigate(['instructor-licence']);
  }

  candidates(){
    this.router.navigate(['instructor-candidates']);
  }

  requests(){
    this.router.navigate(['instructor-requests']);
  }
}