export interface Order {
  id?: number;
  issueDate: Date;
  deliveryDate: Date;
  address: string;
  total: number;
  isActive: boolean;
  supplierId: number;
}
