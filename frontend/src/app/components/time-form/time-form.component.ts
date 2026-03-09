import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, FormArray, FormControl } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Integrante } from '../../models/integrante.model';
import { Time } from '../../models/time.model';

@Component({
  selector: 'app-time-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './time-form.component.html'
})
export class TimeFormComponent implements OnInit {
  form: FormGroup;
  integrantesDisponiveis: Integrante[] = [];
  timesCadastrados: Time[] = [];

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    this.form = this.fb.group({
      data: ['', Validators.required],
      integranteIds: this.fb.array([], Validators.required)
    });
  }

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados() {
    this.apiService.getIntegrantes().subscribe(res => this.integrantesDisponiveis = res);
    this.apiService.getTimes().subscribe(res => this.timesCadastrados = res);
  }

  onCheckboxChange(e: any) {
    const checkArray: FormArray = this.form.get('integranteIds') as FormArray;
    if (e.target.checked) {
      checkArray.push(new FormControl(e.target.value));
    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: any) => {
        if (item.value == e.target.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  salvar() {
    if (this.form.valid) {
      // Convertemos os valores para Number antes de enviar
      const payload = {
        data: this.form.value.data,
        integranteIds: this.form.value.integranteIds.map((id: string) => Number(id))
      };

      this.apiService.salvarTime(payload).subscribe(() => {
        alert('Time escalado com sucesso!');
        this.form.reset();
        // Limpa o FormArray manualmente após o reset
        (this.form.get('integranteIds') as FormArray).clear();
        this.carregarDados();
      });
    }
  }
}
