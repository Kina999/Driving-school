import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-instructor-calendar',
  templateUrl: './instructor-calendar.component.html',
  styleUrls: ['./instructor-calendar.component.css']
})
export class InstructorCalendarComponent implements OnInit {

  categoryAndType: string = '';
  terminDate = new Date();
  startTime: any;
  endTime: any;
  licences: any = [];
  terminDates: any = [];
  terminTimes: any = [];
  instructorTermins: any = [];

  @ViewChild('closeModal3') closeModal3: any;

  constructor(private router: Router, private http: HttpClient, public datepipe: DatePipe) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if(user != null){
      var email = JSON.parse(user).email;
      this.http.get('http://localhost:8080/licences/getAll?email=' + JSON.parse(user).email).subscribe(
        (data: any) => {
          this.licences = data;
      });
      this.http.get('http://localhost:8080/termins/getAllInstructorTermins?instructorEmail=' + JSON.parse(user).email).subscribe(
        (data: any) => {
          this.instructorTermins = data;
      });
      this.http.get('http://localhost:8080/termins/getAllInstructorTerminDates?instructorEmail=' + JSON.parse(user).email).subscribe(
        (data: any) => {  
          this.terminDates = data;
          this.terminDates.forEach((date : any, i : number) => {
            this.http.get('http://localhost:8080/termins/getAllInstructorTerminTimesForDate?instructorEmail=' + email + "&date=" + date).subscribe(
              data => {  
                this.terminTimes[i] = data;
            });
          });
      });
      
    }
  }
  
  convertDate(date: any){
    return this.datepipe.transform(date, 'HH:mm')
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

  addTermin(){
    if(this.startTime == undefined || this.endTime == undefined || this.categoryAndType === ''){
      alert("Molimo unesite sva polja!");
    }else if(this.startTime.split(':')[0] > this.endTime.split(':')[0] || (this.startTime.split(':')[0] === this.endTime.split(':')[0] && this.startTime.split(':')[1] > this.endTime.split(':')[1])){
      alert("Neispravni termini!");
    }else{
      var startDate = new Date(this.terminDate);
      startDate.setHours(this.startTime.split(':')[0])
      startDate.setMinutes(this.startTime.split(':')[1])
      var endDate = new Date(this.terminDate);
      endDate.setHours(this.endTime.split(':')[0])
      endDate.setMinutes(this.endTime.split(':')[1])
      var user = localStorage.getItem('currentUser');
      if(user != null){
        var userEmail = JSON.parse(user).email;
        var body = {startTime: startDate,
          endTime: endDate,
          categoryAndType: this.categoryAndType,
          instructorEmail: userEmail};
        this.http.post('http://localhost:8080/termins/addTermin', body)
        .subscribe(data => {if(data){
          this.closeModal3.nativeElement.click();
          alert("Sucess")
          var user = localStorage.getItem('currentUser');
          if(user != null){
            var email = JSON.parse(user).email;
            this.http.get('http://localhost:8080/termins/getAllInstructorTermins?instructorEmail=' + JSON.parse(user).email).subscribe(
              (data: any) => {
                this.instructorTermins = data;
            });
            this.http.get('http://localhost:8080/termins/getAllInstructorTerminDates?instructorEmail=' + JSON.parse(user).email).subscribe(
              (data: any) => {  
                this.terminDates = data;
                this.terminDates.forEach((date : any, i : number) => {
                  this.http.get('http://localhost:8080/termins/getAllInstructorTerminTimesForDate?instructorEmail=' + email + "&date=" + date).subscribe(
                    (data: any) => { 
                      this.terminTimes[i] = data;
                  });
                });
            });
            
          }
        }
          else{alert("Termin se preklapa sa vec postojecim ili je licenca istekla!")}});
      }
    }
 }
}
