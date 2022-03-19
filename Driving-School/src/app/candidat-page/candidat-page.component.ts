import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-candidat-page',
  templateUrl: './candidat-page.component.html',
  styleUrls: ['./candidat-page.component.css']
})
export class CandidatPageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logout(){
    localStorage.removeItem('currentUser');
    this.router.navigate(['']);
  }

  account(){
    this.router.navigate(['candidate-account']);
  }
}
