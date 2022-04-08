import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-admin-candidates',
  templateUrl: './admin-candidates.component.html',
  styleUrls: ['./admin-candidates.component.css']
})
export class AdminCandidatesComponent implements OnInit {

  allCandidates: any = [];
  cancelingData: any = [];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/candidates/allCandidates').subscribe(
      (data: any) => {
        this.allCandidates = data;
        this.allCandidates.forEach((candidate: any, i: number) => {
          this.http.get('http://localhost:8080/termins/candidateCancelingData?email=' + candidate.email).subscribe(
            (data: any) => {
              this.cancelingData[i] = data;
            });
        });
      });
  }
  tests(){
    this.router.navigate(['admin-tests']);
  }
  blockUser(i: number) {
    this.http.get('http://localhost:8080/candidates/blockCandidate?candidateEmail=' + this.allCandidates[i].email).subscribe(
      (data: any) => {
        if (data) {
          alert("Succes");
          this.http.get('http://localhost:8080/candidates/allCandidates').subscribe(
            (data: any) => {
              this.allCandidates = data;
              this.allCandidates.forEach((candidate: any, i: number) => {
                this.http.get('http://localhost:8080/termins/candidateCancelingData?email=' + candidate.email).subscribe(
                  (data: any) => {
                    this.cancelingData[i] = data;
                  });
              });
            });
        } else { alert("An error occured...") }
      });
  }

  unblockUser(i: number) {
    this.http.get('http://localhost:8080/candidates/unblockCandidate?candidateEmail=' + this.allCandidates[i].email).subscribe(
      (data: any) => {
        if (data) {
          alert("Succes");

          this.http.get('http://localhost:8080/candidates/allCandidates').subscribe(
            (data: any) => {
              this.allCandidates = data;
              this.allCandidates.forEach((candidate: any, i: number) => {
                this.http.get('http://localhost:8080/termins/candidateCancelingData?email=' + candidate.email).subscribe(
                  (data: any) => {
                    this.cancelingData[i] = data;
                  });
              });
            });
        } else { alert("An error occured...") }
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

}
