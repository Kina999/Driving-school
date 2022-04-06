import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-candidate-account',
  templateUrl: './candidate-account.component.html',
  styleUrls: ['./candidate-account.component.css']
})
export class CandidateAccountComponent implements OnInit {

  email: string = '';
  password: string = '';
  newPassword: string = '';
  reenteredPassword: string = '';
  name: string = '';
  lastName: string = '';
  phoneNumber: string = '';
  progress: any;
  theoreticalProgress: number = 0;
  practicalProgress: number = 0;
  drivingLicenceProgress: number = 0;

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    var currentUser = localStorage.getItem('currentUser');
    if(currentUser != null){
      var user = JSON.parse(currentUser);
      this.email = user.email;
      this.password = user.password;
      this.name = user.name;
      this.lastName = user.lastName;
      this.phoneNumber = user.phoneNumber;
      this.http.get('http://localhost:8080/candidates/getCandidateProgress?candidateEmail=' + this.email).subscribe(
        (data: any) => {
          this.progress = data; 
          this.theoreticalProgress = data.theoreticalDone / data.necessaryClasses * 100;
          this.practicalProgress = data.practicalDone / data.necessaryClasses * 100;
          this.drivingLicenceProgress = (data.practicalDone + data.theoreticalDone) / (2 * data.necessaryClasses) * 100;
        });
    }
  }
  tests() {
    this.router.navigate(['candidate-tests']);
  }
  logout(){
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  account(){
    this.router.navigate(['candidate-account']);
  }

  instructor(){
    this.router.navigate(['candidat-instructor']);
  }
  editProfile(){
    if(this.password != this.newPassword && this.newPassword != ''){
      this.password = this.newPassword
    }
    if(this.newPassword != this.reenteredPassword){
      alert("Passwords not matching!");
      return;
    }
    var body = {email: this.email,
      password: this.password,
      name: this.name,
      lastName: this.lastName,
      phoneNumber: this.phoneNumber};
    this.http.post('http://localhost:8080/candidates/editProfile', body)
      .subscribe(data => {
        if(data){alert("Successs")
        var currentUser = localStorage.getItem('currentUser');
        if(currentUser != null){
          var user = JSON.parse(currentUser);
          user.name = this.name;
          user.password = this.password;
          user.lastName = this.lastName;
          user.phoneNumber = this.phoneNumber;
          localStorage.setItem('currentUser', JSON.stringify(user));
        }
      }else{alert("An error occured... Please try again")}});

  }
  termins(){
    this.router.navigate(['candidate-termins']);
  }
}
