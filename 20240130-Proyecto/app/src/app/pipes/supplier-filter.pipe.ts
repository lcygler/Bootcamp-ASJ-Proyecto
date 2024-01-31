import { Pipe, PipeTransform } from '@angular/core';
import { Supplier } from '../models/supplier/ISupplier';

@Pipe({
  name: 'supplierFilter',
})
export class SupplierFilterPipe implements PipeTransform {
  transform(array: Supplier[], filter: number): Supplier[] {
    if (!filter) return array;

    if (filter === 1) {
      // Order by business name ascending
      return array
        .slice()
        .sort((a, b) => a.businessName.localeCompare(b.businessName));
    } else if (filter === 2) {
      // Order by business name descending
      return array
        .slice()
        .sort((a, b) => b.businessName.localeCompare(a.businessName));
    } else {
      return array;
    }
  }
}
