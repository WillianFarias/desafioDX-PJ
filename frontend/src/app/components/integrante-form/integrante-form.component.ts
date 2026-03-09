import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Integrante } from '../../models/integrante.model';

@Component({
  selector: 'app-integrante-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './integrante-form.component.html'
})
export class IntegranteFormComponent implements OnInit {
  form: FormGroup;
  integrantes: Integrante[] = [];

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      franquia: ['', Validators.required],
      funcao: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.carregarIntegrantes();
  }

  carregarIntegrantes() {
    this.apiService.getIntegrantes().subscribe(res => this.integrantes = res);
  }

  salvar() {
    if (this.form.valid) {
      this.apiService.salvarIntegrante(this.form.value).subscribe(() => {
        this.form.reset();
        this.carregarIntegrantes();
        alert('Integrante salvo com sucesso!');
      });
    }
  }
}
