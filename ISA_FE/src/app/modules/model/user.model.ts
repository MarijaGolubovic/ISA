export class UserResponse {
    name: string = '';
    surname: string = '';
    email: string = '';
    phoneNumber: string = '';

    public constructor(obj?: any) {
        if (obj) {
            this.name = obj.name;
            this.surname = obj.surname;
            this.email = obj.email;
            this.phoneNumber = obj.phoneNumber;        
        }
    }
}