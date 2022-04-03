import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-front-page',
  templateUrl: './front-page.component.html',
  styleUrls: ['./front-page.component.css']
})
export class FrontPageComponent implements OnInit {

  latitude = 45.267136;
  longitude = 19.833549;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.router.navigate(['login']);
  }

  register(){
    this.router.navigate(['registration']);
  }
}
