import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';
@Component({
  selector: 'app-candidate-tests',
  templateUrl: './candidate-tests.component.html',
  styleUrls: ['./candidate-tests.component.css']
})
export class CandidateTestsComponent implements OnInit {

  terminDates: any = [];
  terminTimes: any = [];
  reservedTest: any;
  progress: any;

  constructor(private router: Router, private http: HttpClient, public datepipe: DatePipe) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var userEmail = JSON.parse(user).email;
      this.http.get('http://localhost:8080/candidates/candidateProgress?candidateEmail=' + userEmail).subscribe(
        (data: any) => {
          this.progress = data;
        });
      this.http.get('http://localhost:8080/drivingTest/reservedTestForCandidate?candidateEmail=' + userEmail).subscribe(
        (data: any) => {
          this.reservedTest = data;
        });
      this.http.get('http://localhost:8080/drivingTest/testsDatesForCandidate?candidateEmail=' + userEmail).subscribe(
        (data: any) => {
          this.terminDates = data;
          this.terminDates.forEach((date: any, i: number) => {
            this.http.get('http://localhost:8080/drivingTest/testsForCandidate?candidateEmail=' + userEmail + "&date=" + date).subscribe(
              (data: any) => {
                this.terminTimes[i] = data;
              });
          });
        });
    }
  }
  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }
  tests() {
    this.router.navigate(['candidate-tests']);
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
  convertDate(date: any) {
    return this.datepipe.transform(date, 'HH:mm')
  }
  scheduleTest(i: number, j: number) {
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var userEmail = JSON.parse(user).email;
      var body = {
        clientEmail: userEmail,
        terminId: this.terminTimes[i][j].id
      };
      this.http.post('http://localhost:8080/drivingTest/scheduleTest', body)
        .subscribe(data => {
          this.http.get('http://localhost:8080/candidates/candidateProgress?candidateEmail=' + userEmail).subscribe(
            (data: any) => {
              this.progress = data;
            });
          this.http.get('http://localhost:8080/drivingTest/reservedTestForCandidate?candidateEmail=' + userEmail).subscribe(
            (data: any) => {
              this.reservedTest = data;
            });
          this.http.get('http://localhost:8080/drivingTest/testsDatesForCandidate?candidateEmail=' + userEmail).subscribe(
            (data: any) => {
              this.terminDates = data;
              this.terminDates.forEach((date: any, i: number) => {
                this.http.get('http://localhost:8080/drivingTest/testsForCandidate?candidateEmail=' + userEmail + "&date=" + date).subscribe(
                  (data: any) => {
                    this.terminTimes[i] = data;
                  });
              });
            });
        });
    }
  }
}
