import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-admin-tests',
  templateUrl: './admin-tests.component.html',
  styleUrls: ['./admin-tests.component.css']
})
export class AdminTestsComponent implements OnInit {

  categoryAndType: string = '';
  terminDate = new Date();
  startTime: any;
  licences: any = [];
  terminDates: any = [];
  terminTimes: any = [];
  selectedTermin: any;

  @ViewChild('closeModal4') closeModal4: any;
  @ViewChild('closeModal5') closeModal5: any;

  constructor(private router: Router, private http: HttpClient, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/licences/all?email=').subscribe(
      (data: any) => {
        this.licences = data;
      });
    this.http.get('http://localhost:8080/drivingTest/testDates').subscribe(
      (data: any) => {
        this.terminDates = data;
        this.terminDates.forEach((date: any, i: number) => {
          this.http.get('http://localhost:8080/drivingTest/testsForDate?date=' + date).subscribe(
            data => {
              this.terminTimes[i] = data;
            });
        });
      });
  }

  passedTest() {
    const body = { title: 'Pass test' };
    this.http.put<any>('http://localhost:8080/drivingTest/passTest/' + this.selectedTermin.id, body)
      .subscribe(data => {
        this.closeModal5.nativeElement.click();
        this.http.get('http://localhost:8080/drivingTest/testDates').subscribe(
          (data: any) => {
            this.terminDates = data;
            this.terminDates.forEach((date: any, i: number) => {
              this.http.get('http://localhost:8080/drivingTest/testsForDate?date=' + date).subscribe(
                data => {
                  this.terminTimes[i] = data;
                });
            });
          });
      });
  }

  failedTest() {
    const body = { title: 'Fail test' };
    this.http.put<any>('http://localhost:8080/drivingTest/failTest/' + this.selectedTermin.id, body)
      .subscribe(data => {
        this.closeModal5.nativeElement.click();
        this.http.get('http://localhost:8080/drivingTest/testDates').subscribe(
          (data: any) => {
            this.terminDates = data;
            this.terminDates.forEach((date: any, i: number) => {
              this.http.get('http://localhost:8080/drivingTest/testsForDate?date=' + date).subscribe(
                data => {
                  this.terminTimes[i] = data;
                });
            });
          });
      });
  }

  setSelectedTermin(i: number, j: number) {
    this.selectedTermin = this.terminTimes[i][j];
  }

  removeTest(i: number, j: number) {
    this.http.delete('http://localhost:8080/drivingTest/deleteTest/' + this.terminTimes[i][j].id)
      .subscribe(data => {
        this.http.get('http://localhost:8080/drivingTest/testDates').subscribe(
          (data: any) => {
            this.terminDates = data;
            this.terminDates.forEach((date: any, i: number) => {
              this.http.get('http://localhost:8080/drivingTest/testsForDate?date=' + date).subscribe(
                data => {
                  this.terminTimes[i] = data;
                });
            });
          });
      });
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  instructors() {
    this.router.navigate(['instructors']);
  }

  candidates() {
    this.router.navigate(['admin-candidates']);
  }

  tests() {
    this.router.navigate(['admin-tests']);
  }

  addTermin() {
    if (this.startTime == undefined || this.categoryAndType === '') {
      alert("Molimo unesite sva polja!");
    } else {
      var startDate = new Date(this.terminDate);
      startDate.setHours(this.startTime.split(':')[0])
      startDate.setMinutes(this.startTime.split(':')[1])

      var body = {
        dateAndTime: startDate,
        categoryAndType: this.categoryAndType,
      };
      this.http.post('http://localhost:8080/drivingTest/addTest', body)
        .subscribe(data => {
          if (data) {
            this.closeModal4.nativeElement.click();
            this.http.get('http://localhost:8080/drivingTest/testDates').subscribe(
              (data: any) => {
                this.terminDates = data;
                this.terminDates.forEach((date: any, i: number) => {
                  this.http.get('http://localhost:8080/drivingTest/testsForDate?date=' + date).subscribe(
                    data => {
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
}
