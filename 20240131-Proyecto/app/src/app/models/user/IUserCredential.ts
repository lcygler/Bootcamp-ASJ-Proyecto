import { User } from './IUser';

export interface UserCredential {
  id: number;
  user: User;
  password: string;
}
