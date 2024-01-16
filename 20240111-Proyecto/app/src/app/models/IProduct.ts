export interface Product {
  id?: number;
  sku: string;
  category: string;
  name: string;
  description: string;
  price: number | null;
  isDeleted: boolean;
  supplierId: number | null;
}
