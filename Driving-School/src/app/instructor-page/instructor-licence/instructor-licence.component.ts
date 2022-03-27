import { Component, OnInit, ViewChild} from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-instructor-licence',
  templateUrl: './instructor-licence.component.html',
  styleUrls: ['./instructor-licence.component.css']
})
export class InstructorLicenceComponent implements OnInit {

  licences: any = [];
  expirationDate = new Date();
  licenceType: string = '';
  category: string = '';
  
  @ViewChild('closeModal1') closeModal1: any;

  constructor(public datepipe: DatePipe, private router : Router, private http: HttpClient) { }

  ngOnInit(): void {
    var user = localStorage.getItem('currentUser');
    if(user != null){
      this.http.get('http://localhost:8080/licences/getAll?email=' + JSON.parse(user).email).subscribe(
        data => {
          this.licences = data;
      });
    }
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
  convertDate(date: any){
    return this.datepipe.transform(date, 'dd-MM-yyyy')
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
  
  addLicence(){
    if(this.licenceType === '' || this.category === ''){
      alert("Please enter all fields")
    }else{
      var user = localStorage.getItem('currentUser');
      if(user != null){
        var userEmail = JSON.parse(user).email;
        var body = {expirationDate: this.expirationDate,
          licenceType: this.licenceType,
          category: this.category,
          email: userEmail};
        this.http.post('http://localhost:8080/licences/addLicence', body)
        .subscribe(data => {if(data){
          this.http.get('http://localhost:8080/licences/getAll?email=' + userEmail).subscribe(
            data => {
              this.licences = data;
          });
          this.closeModal1.nativeElement.click();
          alert("Sucess")}else{alert("Licence type already exists!")}});
        }
    }
  }
   
}
