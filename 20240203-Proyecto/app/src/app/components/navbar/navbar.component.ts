import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {
  faBuilding,
  faFileLines,
  faGear,
  faHouse,
  faTags,
  faUsers,
} from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/services/auth/auth.service';

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
  faHouse = faHouse;

  constructor(private router: Router, private authService: AuthService) {}

  isCurrentRoute(route: string): boolean {
    return route === this.router.url;
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }
}
