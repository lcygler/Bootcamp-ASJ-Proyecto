import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Industry } from '../../models/supplier/IIndustry';

@Injectable({
  providedIn: 'root',
})
export class IndustryService {
  private API_URL = 'http://localhost:8080/industries';

  constructor(private http: HttpClient) {}

  public getIndustries(): Observable<Industry[]> {
    return this.http.get<Industry[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching industry list:', error);
        return throwError(() => error);
      })
    );
  }

  public getIndustryById(id: number): Observable<Industry> {
    return this.http.get<Industry>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching industry by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createIndustry(industry: Industry): Observable<Industry> {
    return this.http.post<Industry>(`${this.API_URL}`, industry).pipe(
      catchError((error) => {
        console.error('Error creating industry:', error);
        return throwError(() => error);
      })
    );
  }

  public updateIndustry(
    id: number,
    industry: Partial<Industry>
  ): Observable<Industry> {
    return this.http.patch<Industry>(`${this.API_URL}/${id}`, industry).pipe(
      catchError((error) => {
        console.error('Error updating industry:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteIndustry(id: number): Observable<Industry> {
    return this.http.delete<Industry>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting industry:', error);
        return throwError(() => error);
      })
    );
  }
}
