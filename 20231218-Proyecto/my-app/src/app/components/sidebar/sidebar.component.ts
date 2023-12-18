import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  constructor(private router: Router) {}

  isCurrentRoute(route: string): boolean {
    return route === this.router.url;
    // return this.router.isActive(route, true);
  }
}
