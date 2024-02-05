import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedIn = false;

  constructor() {
    this.loadAuthState();
  }

  login() {
    this.isLoggedIn = true;
    this.saveAuthState();
  }

  logout() {
    this.isLoggedIn = false;
    this.saveAuthState();
  }

  isAuthenticated() {
    return this.isLoggedIn;
  }

  private saveAuthState() {
    localStorage.setItem('isLoggedIn', String(this.isLoggedIn));
  }

  private loadAuthState() {
    const storedAuthState = localStorage.getItem('isLoggedIn');
    this.isLoggedIn = storedAuthState ? JSON.parse(storedAuthState) : false;
  }
}
