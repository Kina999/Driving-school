import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
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

  @ViewChild('closeModal4') closeModal4: any;

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/licences/getAll?email=').subscribe(
      (data: any) => {
        this.licences = data;
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
        .subscribe(data => {if (data) {this.closeModal4.nativeElement.click();alert("Sucess")}});
    }
  }

}
