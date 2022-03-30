import { Component, OnInit, SystemJsNgModuleLoader, ViewChild } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-candidat-instructor',
  templateUrl: './candidat-instructor.component.html',
  styleUrls: ['./candidat-instructor.component.css']
})
export class CandidatInstructorComponent implements OnInit {

  instructorChoosen: boolean = false;
  requestApproved: boolean = false;
  instructorsList: any = [];
  selectedInstructor: any;
  candidateInstructor: any;
  requestedInstructorInfo: any;
  category: string = '';
  terminDates: any = [];
  terminTimes: any = [];
  instructorTermins: any = [];
  @ViewChild('closeModal2') closeModal2: any;

  constructor(private router: Router, private http: HttpClient, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/instructors/getAll').subscribe(
      data => {
        this.instructorsList = data;
      });
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var userEmail = JSON.parse(user).email;
      this.http.get('http://localhost:8080/termins/getAllCandidatePossibleTerminDates?candidateEmail=' + JSON.parse(user).email).subscribe(
        (data: any) => {
          this.terminDates = data;
          this.terminDates.forEach((date: any, i: number) => {
            this.http.get('http://localhost:8080/termins/getAllCandidatePossibleTerminForDate?candidateEmail=' + userEmail + "&date=" + date).subscribe(
              data => {
                this.terminTimes[i] = data;
              });
          });
        });
      this.http.get('http://localhost:8080/instructorRequests/getInstructorRequest?email=' + userEmail).subscribe(
        (data: any) => {
          if (data != null) {
            if (data.approved && !data.refused) {
              this.http.get('http://localhost:8080/candidates/getInstructor?email=' + userEmail).subscribe(
                data => {
                  if (data != null) {
                    this.candidateInstructor = data;
                    this.instructorChoosen = true;
                    this.requestApproved = true;
                  } else {
                    this.instructorChoosen = false;
                    this.requestApproved = true;
                  }
                });
              this.instructorChoosen = true;
              this.requestApproved = true;
            } else if (!data.approved && !data.refused) {
              this.http.get('http://localhost:8080/instructors/getInstructor?instructorEmail=' + data.instructorEmail).subscribe(
                (data: any) => {
                  this.requestedInstructorInfo = data;
                });
              this.instructorChoosen = true;
              this.requestApproved = false;
            } else if (data.refused) {
              this.instructorChoosen = false;
            }
          } else {
            this.instructorChoosen = false;
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

  instructor() {
    this.router.navigate(['candidat-instructor']);
  }
  account() {
    this.router.navigate(['candidate-account']);
  }
  instructorSelected(instructor: any) {
    this.selectedInstructor = instructor;
  }
  cancelChoosingInstructor() {
    this.closeModal2.nativeElement.click();
  }
  chooseSelectedInstructor() {
    var user = localStorage.getItem('currentUser');
    if (user != null) {
      var email = JSON.parse(user).email;
      var body = {
        candidateEmail: email,
        instructorEmail: this.selectedInstructor.email,
        category: this.category
      };
      this.http.post('http://localhost:8080/instructorRequests/addInstructorRequest', body)
        .subscribe(data => {
          if (data) {
            this.http.get('http://localhost:8080/instructors/getInstructor?instructorEmail=' + this.selectedInstructor.email).subscribe(
              (data: any) => {
                this.requestedInstructorInfo = data;
              });
            this.instructorChoosen = true;
            this.requestApproved = false;
            alert("Sucess");
            var user = localStorage.getItem('currentUser');
            if (user != null) {
              localStorage.removeItem('currentUser');
              var newUser = JSON.parse(user);
              newUser.instructor = this.selectedInstructor;
              localStorage.setItem('currentUser', newUser);
            }
            this.closeModal2.nativeElement.click();
          } else { alert("An error occured... Please try again!") }
        });

    }
  }

  categoryChanged(event: any) {
    this.http.get('http://localhost:8080/licences/getAllWithCategory?category=' + this.category).subscribe(
      data => {
        this.instructorsList = data;
      });
  }
}
