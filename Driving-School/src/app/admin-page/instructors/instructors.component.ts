import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-instructors',
  templateUrl: './instructors.component.html',
  styleUrls: ['./instructors.component.css']
})
export class InstructorsComponent implements OnInit {

  email: string = '';
  password: string = '';
  reenteredPassword: string = '';
  name: string = '';
  lastName: string = '';
  phoneNumber: string = '';
  instructorsList: any = [];

  @ViewChild('closeModal') closeModal: any;

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/instructors/getAll').subscribe(
      data => {
        this.instructorsList = data;
      });
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  instructors() {
    this.router.navigate(['instructors']);
  }
  register() {
    if (this.password != this.reenteredPassword) {
      alert("Passwords not matching");
    } else {
      var body = {
        email: this.email,
        password: this.password,
        name: this.name,
        lastName: this.lastName,
        phoneNumber: this.phoneNumber
      };
      this.http.post('http://localhost:8080/instructors/registration', body)
        .subscribe(data => {
          if (data) {
            this.http.get('http://localhost:8080/instructors/getAll').subscribe(
              data => {
                this.instructorsList = data;
              });
            this.email = '';
            this.password = '';
            this.reenteredPassword = '';
            this.name = '';
            this.lastName = '';
            this.phoneNumber = '';
            this.closeModal.nativeElement.click();
          } else { alert("Username already exists") }
        });
    }
  }
  candidates(){
    this.router.navigate(['admin-candidates']);
  }
  tests(){
    this.router.navigate(['admin-tests']);
  }
}

