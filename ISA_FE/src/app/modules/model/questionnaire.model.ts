export class QUestionnaireRespons{
    question1:boolean=false;
    question2:boolean=false;
    question3:boolean=false;
    question4:boolean=false;
    question5:boolean=false;
    question6:boolean=false;
    question7:boolean=false;
    question8:boolean=false;
    question9:boolean=false;
    question10:boolean=false;
    question11:boolean=false;
    question12:boolean=false;

    public constructor(obj?: any){
        if (obj) {
            this.question1=obj.question1;
            this.question2=obj.question2;
            this.question3=obj.question3;
            this.question4=obj.question4;
            this.question5=obj.question5;
            this.question6=obj.question6;
            this.question7=obj.question7;
            this.question8=obj.question8;
            this.question9=obj.question9;
            this.question10=obj.question10;
            this.question11=obj.question11;
            this.question12=obj.question12;
            }
    }
}