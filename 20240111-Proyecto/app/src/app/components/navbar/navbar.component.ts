import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {
  faBuilding,
  faFileLines,
  faTags,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  faBuilding = faBuilding;
  faTags = faTags;
  faFileLines = faFileLines;

  constructor(private router: Router) {}

  isCurrentRoute(route: string): boolean {
    return route === this.router.url;
  }
}
