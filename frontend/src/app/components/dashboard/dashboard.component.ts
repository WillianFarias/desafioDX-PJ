import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Integrante } from '../../models/integrante.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  // Filtros
  dataInicio: string = '';
  dataFim: string = '';

  // Resultados
  integranteMaisUsado: Integrante | null = null;
  funcaoMaisComum: string = '';
  franquiaMaisFamosa: string = '';
  contagemFranquia: any = {};
  contagemFuncao: any = {};

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.atualizarConsultas();
  }

  atualizarConsultas() {
    const inicio = this.dataInicio || undefined;
    const fim = this.dataFim || undefined;

    this.apiService.getIntegranteMaisUsado(inicio, fim).subscribe(res => this.integranteMaisUsado = res);
    this.apiService.getFuncaoMaisComum(inicio, fim).subscribe(res => this.funcaoMaisComum = res['Função']);
    this.apiService.getFranquiaMaisFamosa(inicio, fim).subscribe(res => this.franquiaMaisFamosa = res['Franquia']);
    this.apiService.getContagemPorFranquia(inicio, fim).subscribe(res => this.contagemFranquia = res);
    this.apiService.getContagemPorFuncao(inicio, fim).subscribe(res => this.contagemFuncao = res);
  }

  // Helper para transformar o Map do Java em array para o *ngFor
  getEntries(obj: any) {
    return Object.entries(obj);
  }
}
