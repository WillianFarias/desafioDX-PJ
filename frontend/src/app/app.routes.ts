import { Routes } from '@angular/router';
import { IntegranteFormComponent } from './components/integrante-form/integrante-form.component';
import { TimeFormComponent } from './components/time-form/time-form.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
  { path: 'integrantes', component: IntegranteFormComponent },
  { path: 'times', component: TimeFormComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }
];
