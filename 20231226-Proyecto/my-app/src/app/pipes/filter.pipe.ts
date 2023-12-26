import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
})
export class FilterPipe implements PipeTransform {
  transform(array: any[], searchTerm: any): any {
    if (!searchTerm) {
      console.error('FilterPipe: searchTerm must be defined.');
      return array;
    }

    return array.filter((item) =>
      JSON.stringify(item).toLowerCase().includes(searchTerm.toLowerCase())
    );
  }
}
