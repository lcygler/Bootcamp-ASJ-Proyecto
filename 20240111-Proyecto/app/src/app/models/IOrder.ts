export interface Order {
  id?: number;
  issueDate: string | null;
  deliveryDate: Date | null;
  comments: string;
  total: number | null;
  isActive: boolean;
  supplierId: number | null;
}
