import { Pipe, PipeTransform } from '@angular/core';
import { Supplier } from '../../models/supplier/ISupplier';

@Pipe({
  name: 'locationSort',
})
export class LocationSortPipe implements PipeTransform {
  transform(array: Supplier[], filter: string): Supplier[] {
    if (!filter) return array;

    if (filter === 'Ascending') {
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
    } else if (filter === 'Descending') {
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
