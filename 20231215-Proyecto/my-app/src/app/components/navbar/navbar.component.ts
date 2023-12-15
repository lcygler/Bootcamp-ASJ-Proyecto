import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  constructor(private router: Router) {}

  isCurrentRoute(route: string): boolean {
    return route === this.router.url;
    // return this.router.isActive(route, true);
  }
}
