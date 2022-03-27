import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-instructor-page',
  templateUrl: './instructor-page.component.html',
  styleUrls: ['./instructor-page.component.css']
})
export class InstructorPageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
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
}
