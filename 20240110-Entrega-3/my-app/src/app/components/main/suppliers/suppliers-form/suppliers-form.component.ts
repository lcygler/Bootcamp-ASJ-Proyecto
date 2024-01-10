import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LocationService } from 'src/app/services/location.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { ToastService } from 'src/app/services/toast.service';
import { Supplier } from '../../../../models/ISupplier';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css'],
})
export class SuppliersFormComponent implements OnInit {
  supplier: Supplier = {
    code: '',
    businessName: '',
    industry: '',
    website: '',
    email: '',
    phone: '',
    address: {
      street: '',
      number: '',
      postalCode: '',
      city: '',
      state: '',
      country: '',
    },
    taxInformation: {
      cuit: '',
      vatCondition: '',
    },
    contactDetails: {
      firstName: '',
      lastName: '',
      phone: '',
      email: '',
      role: '',
    },
    isDeleted: false,
  };
  supplierId: number | null = null;
  nextSupplierId: number | null = null;
  isEditView: boolean = false;
  vatConditions: any[] = [];
  countryList: any[] = [];
  stateList: any[] = [];
  filteredStates: any[] = [];
  filteredCities: any[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private supplierService: SupplierService,
    private toastService: ToastService,
    private locationService: LocationService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('supplierId');

    if (id) {
      this.supplierId = parseInt(id);
      this.getSupplierById(this.supplierId);
      this.isEditView = this.isEditRoute();
    }

    this.getVatConditions();
    this.countryList = this.locationService.getCountries();
  }

  getSupplierById(id: number): void {
    if (id) {
      this.supplierService.getSupplierById(id).subscribe((res) => {
        this.supplier = res;

        const country = this.supplier.address?.country;
        this.filteredStates = this.locationService.getStatesByCountry(country);

        const state = this.supplier.address?.state;
        this.filteredCities = this.locationService.getCitiesByState(state);
      });
    }
  }

  getVatConditions(): void {
    this.supplierService.getVatConditions().subscribe((res) => {
      this.vatConditions = res;
    });
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      console.error('Invalid form.');
      return;
    }

    this.supplierService.getNextSupplierId().subscribe((res) => {
      const formData = form.value;

      const supplier: Supplier = {
        code: this.supplier.id ? this.supplier.code : `PROV-${res}`,
        businessName: formData.businessName,
        industry: formData.industry,
        website: formData.website,
        email: formData.email,
        phone: formData.phone,
        address: {
          street: formData.street,
          number: formData.number,
          postalCode: formData.postalCode,
          city: formData.city,
          state: formData.state,
          country: formData.country,
        },
        taxInformation: {
          cuit: this.supplier.taxInformation.cuit,
          vatCondition: formData.vatCondition,
        },
        contactDetails: {
          firstName: formData.firstName,
          lastName: formData.lastName,
          phone: formData.contactPhone,
          email: formData.contactEmail,
          role: formData.role,
        },
        isDeleted: this.supplier.id ? this.supplier.isDeleted : false,
      };

      if (this.isEditRoute()) {
        // Update existing supplier
        this.supplierService
          .updateSupplier(this.supplier.id!, supplier)
          .subscribe((res) => {
            this.toastService.showSuccessToast(
              'Proveedor modificado correctamente!'
            );
          });
      } else {
        // Add new supplier
        this.supplierService.createSupplier(supplier).subscribe((res) => {
          this.toastService.showSuccessToast(
            'Proveedor agregado correctamente!'
          );
        });
      }

      form.reset();
      this.router.navigate(['/suppliers']);
    });
  }

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/suppliers/edit');
  }

  onCountryChange(selectedCountry: string): void {
    if (selectedCountry) {
      this.filteredStates =
        this.locationService.getStatesByCountry(selectedCountry);
    } else {
      this.filteredStates = [];
    }
  }

  onStateChange(selectedState: string): void {
    if (selectedState) {
      this.filteredCities =
        this.locationService.getCitiesByState(selectedState);
    } else {
      this.filteredCities = [];
    }
  }

  formatCuit(event: any): void {
    const cuit = event.target.value.replace(/\D/g, ''); // Eliminar no dígitos
    const maxLength = 11;

    if (cuit.length <= maxLength) {
      if (cuit.length <= 2) {
        this.supplier.taxInformation.cuit = cuit;
      } else if (cuit.length <= 10) {
        this.supplier.taxInformation.cuit = `${cuit.substring(
          0,
          2
        )}-${cuit.substring(2, 10)}`;
      } else {
        this.supplier.taxInformation.cuit = `${cuit.substring(
          0,
          2
        )}-${cuit.substring(2, 10)}-${cuit.substring(10, 11)}`;
      }
    }
  }
}
