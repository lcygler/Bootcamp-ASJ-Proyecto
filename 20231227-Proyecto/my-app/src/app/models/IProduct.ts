export interface IProduct {
  id?: number;
  sku: string;
  category: string;
  name: string;
  description: string;
  price: number;
  isDeleted: boolean;
  supplierId: number;
}
