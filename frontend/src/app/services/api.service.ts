import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Integrante } from '../models/integrante.model';
import { Time, TimeRequest } from '../models/time.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly API_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // ==================== INTEGRANTES ====================

  getIntegrantes(): Observable<Integrante[]> {
    return this.http.get<Integrante[]>(`${this.API_URL}/integrantes`);
  }

  salvarIntegrante(integrante: Integrante): Observable<Integrante> {
    return this.http.post<Integrante>(`${this.API_URL}/integrantes`, integrante);
  }

  // ==================== TIMES ====================

  getTimes(): Observable<Time[]> {
    return this.http.get<Time[]>(`${this.API_URL}/times`);
  }

  salvarTime(time: TimeRequest): Observable<Time> {
    return this.http.post<Time>(`${this.API_URL}/times`, time);
  }

  // ==================== ANALYTICS ====================

  getTimeDaData(data: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/analytics/time-da-data`, {
      params: new HttpParams().set('data', data)
    });
  }

  getIntegranteMaisUsado(inicio?: string, fim?: string): Observable<Integrante> {
    let params = new HttpParams();
    if (inicio) params = params.set('inicio', inicio);
    if (fim) params = params.set('fim', fim);
    return this.http.get<Integrante>(`${this.API_URL}/analytics/integrante-mais-usado`, { params });
  }

  getFuncaoMaisComum(inicio?: string, fim?: string): Observable<any> {
    let params = new HttpParams();
    if (inicio) params = params.set('inicio', inicio);
    if (fim) params = params.set('fim', fim);
    return this.http.get<any>(`${this.API_URL}/analytics/funcao-mais-comum`, { params });
  }

  getFranquiaMaisFamosa(inicio?: string, fim?: string): Observable<any> {
    let params = new HttpParams();
    if (inicio) params = params.set('inicio', inicio);
    if (fim) params = params.set('fim', fim);
    return this.http.get<any>(`${this.API_URL}/analytics/franquia-mais-famosa`, { params });
  }

  getContagemPorFranquia(inicio?: string, fim?: string): Observable<any> {
    let params = new HttpParams();
    if (inicio) params = params.set('inicio', inicio);
    if (fim) params = params.set('fim', fim);
    return this.http.get<any>(`${this.API_URL}/analytics/contagem-por-franquia`, { params });
  }

  getContagemPorFuncao(inicio?: string, fim?: string): Observable<any> {
    let params = new HttpParams();
    if (inicio) params = params.set('inicio', inicio);
    if (fim) params = params.set('fim', fim);
    return this.http.get<any>(`${this.API_URL}/analytics/contagem-por-funcao`, { params });
  }
}
