import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-candidate-termins',
  templateUrl: './candidate-termins.component.html',
  styleUrls: ['./candidate-termins.component.css']
})
export class CandidateTerminsComponent implements OnInit {

  terminDates: any = [];
  terminTimes: any = [];

  constructor(private router: Router, private http: HttpClient, public datepipe: DatePipe) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var userEmail = JSON.parse(user).email;
      this.http.get('http://localhost:8080/termins/allCandidateTerminDates?candidateEmail=' + JSON.parse(user).email).subscribe(
        (data: any) => {
          this.terminDates = data;
          this.terminDates.forEach((date: any, i: number) => {
            this.http.get('http://localhost:8080/termins/allCandidateTerminForDate?candidateEmail=' + userEmail + "&date=" + date).subscribe(
              (data: any) => {
                this.terminTimes[i] = data;
              });
          });
        });
    }
  }
  tests() {
    this.router.navigate(['candidate-tests']);
  }
  cancelTermin(i: number, j: number) {
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var userEmail = JSON.parse(user).email;
      this.http.get('http://localhost:8080/termins/cancelTermin?id=' + this.terminTimes[i][j].id + "&candidateEmail=" + userEmail)
        .subscribe((data: any) => {
          if (data) {
            this.http.get('http://localhost:8080/termins/allCandidateTerminDates?candidateEmail=' + userEmail).subscribe(
              (data: any) => {
                this.terminDates = data;
                this.terminDates.forEach((date: any, i: number) => {
                  this.http.get('http://localhost:8080/termins/allCandidateTerminForDate?candidateEmail=' + userEmail + "&date=" + date).subscribe(
                    (data: any) => {
                      this.terminTimes[i] = data;
                    });
                });
              });
          }
        });
    }
  }

  convertDate(date: any) {
    return this.datepipe.transform(date, 'HH:mm')
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  account() {
    this.router.navigate(['candidate-account']);
  }
  instructor() {
    this.router.navigate(['candidat-instructor']);
  }

  termins() {
    this.router.navigate(['candidate-termins']);
  }
}
