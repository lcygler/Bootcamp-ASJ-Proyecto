export interface Order {
  id?: number;
  issueDate: Date | null;
  deliveryDate: Date | null;
  comments: string;
  total: number | null;
  isActive: boolean;
  supplierId: number | null;
}
