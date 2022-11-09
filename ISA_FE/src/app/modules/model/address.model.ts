export class Address {
    id: number = 0;
    country: string = '';
    city: string = '';
    street: string = '';
    number: string = '';

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.country = obj.country;
            this.city = obj.city;
            this.street = obj.street;
            this.number = obj.number;        
        }
    }
}