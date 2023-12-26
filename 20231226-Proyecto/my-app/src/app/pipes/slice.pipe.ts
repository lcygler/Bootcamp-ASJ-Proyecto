import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'slice',
})
export class SlicePipe implements PipeTransform {
  transform(array: any[], currentPage: number, itemsPerPage: number): any[] {
    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    return array.slice(start, end);
  }
}
