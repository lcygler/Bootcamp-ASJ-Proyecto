import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {
  faBuilding,
  faFileLines,
  faGear,
  faTags,
  faUsers,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  faUsers = faUsers;
  faBuilding = faBuilding;
  faTags = faTags;
  faFileLines = faFileLines;
  faGear = faGear;

  constructor(private router: Router) {}

  isCurrentRoute(route: string): boolean {
    return route === this.router.url;
  }
}
