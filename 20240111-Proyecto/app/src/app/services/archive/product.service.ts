import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'src/app/data/products';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  products = [...data];

  constructor(private http: HttpClient) {}

  public getProducts(): any[] {
    return this.products;
  }

  public getProductById(id: string): any | undefined {
    const product = this.products.find((p) => p.id === id);
    return product;
  }

  public createProduct(product: any) {
    if (product) {
      const lastProduct = this.products[this.products.length - 1];
      const newId = lastProduct ? parseInt(lastProduct.id) + 1 : 1;
      product.id = newId;
      this.products.push(product);
    }
  }

  public updateProduct(product: any): boolean {
    let isUpdated = false;
    const index = this.products.indexOf(product);

    if (index !== -1) {
      this.products[index] = product;
      isUpdated = true;
    }

    return isUpdated;
  }

  public deleteProduct(product: any): boolean {
    let isDeleted = false;
    const index = this.products.indexOf(product);

    if (index !== -1) {
      this.products.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
