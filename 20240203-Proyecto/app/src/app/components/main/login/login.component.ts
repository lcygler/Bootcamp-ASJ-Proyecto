import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/models/user/IUserDTO';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ToastService } from 'src/app/services/common/toast.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: UserDTO = {
    email: '',
    password: '',
  };

  showPassword: boolean = false;
  isValid: boolean = true;

  constructor(
    private userService: UserService,
    private toastService: ToastService,
    private router: Router,
    private authService: AuthService
  ) {}

  onSubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    this.userService.loginUser(this.user).subscribe(
      (res) => {
        this.isValid = true;
        this.toastService.showSuccessToast('Inicio de sesión correcto!');
        this.authService.login();
        this.router.navigate(['/home']);
      },
      (error) => {
        this.isValid = false;
        this.toastService.showErrorToast('Credenciales incorrectas');
      }
    );
  }

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }
}
