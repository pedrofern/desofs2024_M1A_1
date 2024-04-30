import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class GlobalService {

    public isLoggedIn = false;

    async updateData(value: boolean) {
        this.isLoggedIn = value;
    }
}