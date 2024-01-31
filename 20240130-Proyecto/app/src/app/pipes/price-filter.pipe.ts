import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../models/product/IProduct';

@Pipe({
  name: 'priceFilter',
})
export class PriceFilterPipe implements PipeTransform {
  transform(array: Product[], filter: number): Product[] {
    if (!filter) return array;

    if (filter === 1) {
      // Order by price ascending
      return array.slice().sort((a, b) => a.price! - b.price!);
    } else if (filter === 2) {
      // Order by price descending
      return array.slice().sort((a, b) => b.price! - a.price!);
    } else {
      return array;
    }
  }
}
