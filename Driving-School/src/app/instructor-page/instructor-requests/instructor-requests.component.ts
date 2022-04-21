import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-instructor-requests',
  templateUrl: './instructor-requests.component.html',
  styleUrls: ['./instructor-requests.component.css']
})
export class InstructorRequestsComponent implements OnInit {

  instructorRequests: any = [];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if(user != null){
      var userEmail = JSON.parse(user).email;
      this.http.get('http://localhost:8080/instructorRequests/instructorRequests?instructorEmail=' + userEmail).subscribe(
        (data: any) => {
          if(data != null){
            this.instructorRequests = data;
          }
      });
    }
  }

  acceptUser(userIndex: number){
    var user = localStorage.getItem('currentUser');
    if(user != null){
      var instructorMail = JSON.parse(user).email}
      var body = {candidateEmail: this.instructorRequests[userIndex].email,
        instructorEmail: instructorMail};
      this.http.post('http://localhost:8080/candidates/addInstructor', body)
        .subscribe(data => {
          if(data){
            this.http.get('http://localhost:8080/instructorRequests/instructorRequests?instructorEmail=' + instructorMail).subscribe(
              (data: any) => {
                if(data != null){
                  this.instructorRequests = data;
                }
             });
          }else{alert("An error occured... Please try again!")}
        });
  }
  
  refuseUser(userIndex: number){
    var user = localStorage.getItem('currentUser');
    if(user != null){
      var instructorMail = JSON.parse(user).email}
      var body = {candidateEmail: this.instructorRequests[userIndex].email,
        instructorEmail: instructorMail};
      this.http.post('http://localhost:8080/instructorRequests/refuseRequest', body)
        .subscribe(data => {
          if(data){
            this.http.get('http://localhost:8080/instructorRequests/instructorRequests?instructorEmail=' + instructorMail).subscribe(
              (data: any) => {
                if(data != null){
                  this.instructorRequests = data;
                }
             });
          }else{alert("An error occured... Please try again!")}
        });
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

  termins(){
    this.router.navigate(['instructor-calendar']);
  }

  history(){
    this.router.navigate(['instructor-history']);
  }
}
