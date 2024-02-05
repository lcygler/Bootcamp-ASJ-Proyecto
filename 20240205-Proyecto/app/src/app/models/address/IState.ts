import { Country } from './ICountry';

export interface State {
  id: number;
  name: string;
  country: Partial<Country>;
}
