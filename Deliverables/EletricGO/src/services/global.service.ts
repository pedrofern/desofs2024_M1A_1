import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class GlobalService {

    public isLoggedIn: boolean = false;

    async updateData(value: boolean) {
        this.isLoggedIn = value;
    }
}