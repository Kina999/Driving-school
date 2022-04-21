import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-instructor-history',
  templateUrl: './instructor-history.component.html',
  styleUrls: ['./instructor-history.component.css']
})
export class InstructorHistoryComponent implements OnInit {

  terminDates: any = [];
  terminTimes: any = [];
  selectedTermin: any;
  
  @ViewChild('closeModal11') closeModal11: any;
  
  constructor(private router: Router, private http: HttpClient, public datepipe: DatePipe) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var email = JSON.parse(user).email;
      this.http.get('http://localhost:8080/termins/passedInstructorTerminDates?instructorEmail=' + JSON.parse(user).email).subscribe(
        (data: any) => {
          this.terminDates = data;
          this.terminDates.forEach((date: any, i: number) => {
            this.http.get('http://localhost:8080/termins/passedInstructorTerminsForDate?instructorEmail=' + email + "&date=" + date).subscribe(
              data => {
                this.terminTimes[i] = data;
              });
          });
        });
    }
  }
  setSelectedTermin(i: number, j: number) {
    this.selectedTermin = this.terminTimes[i][j];
  }
  convertDate(date: any) {
    return this.datepipe.transform(date, 'HH:mm')
  }
  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  account() {
    this.router.navigate(['instructor-account']);
  }

  licence() {
    this.router.navigate(['instructor-licence']);
  }

  candidates() {
    this.router.navigate(['instructor-candidates']);
  }

  requests() {
    this.router.navigate(['instructor-requests']);
  }

  termins() {
    this.router.navigate(['instructor-calendar']);
  }

  history() {
    this.router.navigate(['instructor-history']);
  }
  notShown(clientEmail: string) {
    this.http.put('http://localhost:8080/termins/notShowed/' + this.selectedTermin.id + "/" + clientEmail, {})
      .subscribe(data => {
        this.closeModal11.nativeElement.click();
        var user = localStorage.getItem('currentUser');
        if (user != null) {
          var email = JSON.parse(user).email;
          this.http.get('http://localhost:8080/termins/passedInstructorTerminDates?instructorEmail=' + JSON.parse(user).email).subscribe(
            (data: any) => {
              this.terminDates = data;
              this.terminDates.forEach((date: any, i: number) => {
                this.http.get('http://localhost:8080/termins/passedInstructorTerminsForDate?instructorEmail=' + email + "&date=" + date).subscribe(
                  data => {
                    this.terminTimes[i] = data;
                  });
              });
            });
        }
      });
  }
}
