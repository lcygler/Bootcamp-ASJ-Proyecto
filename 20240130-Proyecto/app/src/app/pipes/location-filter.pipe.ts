import { Pipe, PipeTransform } from '@angular/core';
import { Supplier } from '../models/supplier/ISupplier';

@Pipe({
  name: 'locationFilter',
})
export class LocationFilterPipe implements PipeTransform {
  transform(array: Supplier[], filter: number): Supplier[] {
    if (!filter) return array;

    if (filter === 1) {
      // Order by country and state ascending
      return array
        .slice()
        .sort(
          (a: any, b: any) =>
            a.address.state.country.name
              .toLowerCase()
              .localeCompare(b.address.state.country.name.toLowerCase()) ||
            a.address.state.name
              .toLowerCase()
              .localeCompare(b.address.state.name.toLowerCase())
        );
    } else if (filter === 2) {
      // Order by country and state descending
      return array
        .slice()
        .sort(
          (a: any, b: any) =>
            b.address.state.country.name
              .toLowerCase()
              .localeCompare(a.address.state.country.name.toLowerCase()) ||
            a.address.state.name
              .toLowerCase()
              .localeCompare(b.address.state.name.toLowerCase())
        );
    } else {
      return array;
    }
  }
}
