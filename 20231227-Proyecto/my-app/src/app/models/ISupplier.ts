export interface Supplier {
  id?: number;
  code: string;
  businessName: string;
  industry: string;
  website: string;
  email: string;
  phone: string;
  address: {
    street: string;
    number: string;
    postalCode: string;
    city: string;
    state: string;
    country: string;
  };
  taxInformation: {
    cuit: string;
    vatCondition: string;
  };
  contactDetails: {
    firstName: string;
    lastName: string;
    phone: string;
    email: string;
    role: string;
  };
  isDeleted: boolean;
}
