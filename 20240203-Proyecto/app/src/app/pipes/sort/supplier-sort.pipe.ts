import { Pipe, PipeTransform } from '@angular/core';
import { Supplier } from '../../models/supplier/ISupplier';

@Pipe({
  name: 'supplierSort',
})
export class SupplierSortPipe implements PipeTransform {
  transform(array: Supplier[], filter: string): Supplier[] {
    if (!filter) return array;

    if (filter === 'Ascending') {
      // Order by business name ascending
      return array
        .slice()
        .sort((a, b) => a.businessName.localeCompare(b.businessName));
    } else if (filter === 'Descending') {
      // Order by business name descending
      return array
        .slice()
        .sort((a, b) => b.businessName.localeCompare(a.businessName));
    } else {
      return array;
    }
  }
}
