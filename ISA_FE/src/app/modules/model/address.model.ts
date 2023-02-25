export class Address {
    country: string = '';
    city: string = '';
    street: string = '';
    number: string = '';

    public constructor(obj?: any) {
        if (obj) {
            this.country = obj.country;
            this.city = obj.city;
            this.street = obj.street;
            this.number = obj.number;        
        }
    }
}