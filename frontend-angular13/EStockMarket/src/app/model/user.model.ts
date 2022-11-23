export class UserModel {
    firstname: string | any;
    lastname: string | any;
    username: string | any;
    password: string | any;

    constructor(firstname: string, username: string, password: string,lastname?: string) {
        this.firstname= firstname
        this.lastname = lastname 
        this.username = username 
        this.password = password 
    }

}