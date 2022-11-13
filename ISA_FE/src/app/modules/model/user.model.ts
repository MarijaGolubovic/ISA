import { Address } from "./address.model";
import { BloodBankRequest } from "./bloodBankRequest.model";

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

export class WholeUserResponse {
    id: number = 0;
    name: string = '';
    surname: string = '';
    password: string = ''
    email: string = '';
    phoneNumber: string = '';
    address: Address = new Address()
    URN: string = '';
    gender: Gender = Gender.MALE;
    profession: string = '';
    infoAboutInstitution: string = '';
    userType: UserType = UserType.REGISTERED
    pointsNum: number = 0;
    strikesNum: number = 0;
    userStatus: UserStatus = UserStatus.ACTIVATED;
    userCategory: UserCategory = UserCategory.REGULAR

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.name = obj.name;
            this.surname = obj.surname;
            this.email = obj.email;
            this.password = obj.password;
            this.phoneNumber = obj.phoneNumber;  
            this.address = obj.address;
            this.URN = obj.urn;
            this.gender = obj.gender;
            this.profession = obj.profession;
            this.infoAboutInstitution = obj.infoAboutInstitution;
            this.userType = obj.userType;
            this.pointsNum = obj.pointsNum;
            this.strikesNum = obj.strikesNum;
            this.userCategory = obj.userCategory;
        }
    }
}

enum UserCategory{
    REGULAR,
	SILVER,
	GOLD
}

enum Gender {
    MALE = 0,
    FEMALE = 1
}

export enum UserType{
    REGISTERED,
    UNREGISTERED,
    ADMIN_CENTER,
    ADMIN_SISTEM,
    MEDICAL_STUFF
}

enum UserStatus{
    ACTIVATED,
    NOT_ACTIVATED
}

export class WholeUserResponseWithBloodBank {
    id: number = 0;
    name: string = '';
    surname: string = '';
    password: string = ''
    email: string = '';
    phoneNumber: string = '';
    address: Address = new Address()
    URN: string = '';
    gender: Gender = Gender.MALE;
    profession: string = '';
    infoAboutInstitution: string = '';
    userType: UserType = UserType.ADMIN_CENTER
    pointsNum: number = 0;
    strikesNum: number = 0;
    userStatus: UserStatus = UserStatus.ACTIVATED;
    userCategory: UserCategory = UserCategory.REGULAR;
    bloodBank: BloodBankRequest = new BloodBankRequest();

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.name = obj.name;
            this.surname = obj.surname;
            this.email = obj.email;
            this.password = obj.password;
            this.phoneNumber = obj.phoneNumber;  
            this.address = obj.address;
            this.URN = obj.urn;
            this.gender = obj.gender;
            this.profession = obj.profession;
            this.infoAboutInstitution = obj.infoAboutInstitution;
            this.userType = obj.userType;
            this.pointsNum = obj.pointsNum;
            this.strikesNum = obj.strikesNum;
            this.userCategory = obj.userCategory;
            this.bloodBank = obj.bloodBank;
        }
    }
}