import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { faLock, faUser } from '@fortawesome/free-solid-svg-icons';
import { UserDTO } from 'src/app/models/user/IUserDTO';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ToastService } from 'src/app/services/common/toast.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: UserDTO = {
    email: '',
    password: '',
  };

  showPassword: boolean = false;
  showErrorMessage: boolean = false;
  isValid: boolean = true;

  faLock = faLock;
  faUser = faUser;

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
        this.showErrorMessage = false;
        this.toastService.showSuccessToast('Inicio de sesión exitoso!');
        this.authService.login();
        this.router.navigate(['/home']);
      },
      (error) => {
        this.isValid = false;
        this.showErrorMessage = true;
        // this.toastService.showErrorToast('Credenciales incorrectas');
      }
    );
  }

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }
}
