import { Injectable } from '@angular/core';
import citiesData from '../data/cities.json';
import countriesData from '../data/countries.json';
import statesData from '../data/states.json';

@Injectable({
  providedIn: 'root',
})
export class LocationService {
  constructor() {}

  getCountries() {
    return countriesData;
  }

  getCountryById(countryId: number) {
    return countriesData.filter((country: any) => country.id == countryId)[0];
  }

  getStates() {
    return statesData;
  }

  getStateById(stateId: number) {
    return statesData.filter((state: any) => state.id == stateId)[0];
  }

  getStatesByCountry(country: string) {
    return statesData.filter((state: any) => state.country_name == country);
  }

  getStatesByCountryId(countryId: number) {
    return statesData.filter((state: any) => state.country_id == countryId);
  }

  getCities() {
    return citiesData;
  }

  getCityById(cityId: number) {
    return (citiesData as any).filter((city: any) => city.id == cityId)[0];
  }

  getCitiesByState(state: string) {
    return (citiesData as any).filter((city: any) => city.state_name == state);
  }

  getCitiesByStateId(stateId: number) {
    return (citiesData as any).filter((city: any) => city.state_id == stateId);
  }
}
