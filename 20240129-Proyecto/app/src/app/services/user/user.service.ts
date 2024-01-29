import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../../models/user/IUser';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private API_URL = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching user list:', error);
        return throwError(() => error);
      })
    );
  }

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching user by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.API_URL}`, user).pipe(
      catchError((error) => {
        console.error('Error creating user:', error);
        return throwError(() => error);
      })
    );
  }

  public updateUser(id: number, user: Partial<User>): Observable<User> {
    return this.http.patch<User>(`${this.API_URL}/${id}`, user).pipe(
      catchError((error) => {
        console.error('Error updating user:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteUser(id: number): Observable<User> {
    return this.http.delete<User>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting user:', error);
        return throwError(() => error);
      })
    );
  }
}
