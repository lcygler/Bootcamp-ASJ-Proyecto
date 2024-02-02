import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../../models/product/IProduct';

@Pipe({
  name: 'priceSort',
})
export class PriceSortPipe implements PipeTransform {
  transform(array: Product[], filter: string): Product[] {
    if (!filter) return array;

    if (filter === 'Ascending') {
      // Order by price ascending
      return array.slice().sort((a, b) => a.price! - b.price!);
    } else if (filter === 'Descending') {
      // Order by price descending
      return array.slice().sort((a, b) => b.price! - a.price!);
    } else {
      return array;
    }
  }
}
