export interface Time {
  id?: number;
  data: string;
  composicoes?: any[];
}

export interface TimeRequest {
  data: string;
  integranteIds: number[];
}
