export class CenterResponse {
    id: number = 0;
    name: string = '';
    city: string = '';
    street: string = '';
    streetNumber:string='';
    grade: number = 0;

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.name = obj.name;
            this.city = obj.city;
            this.street = obj.street;
            this.streetNumber=obj.streetNumber;
            this.grade = obj.grade;        
        }
    }
}

