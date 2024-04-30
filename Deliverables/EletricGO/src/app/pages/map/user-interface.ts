import {GUI} from 'dat.gui';

export class UserInterface {
    static gui: GUI;

    static createUserInterface() {
        UserInterface.gui = new GUI({
            hideable: false,
        });
        document.getElementsByTagName("main")[0].style.padding = "0";
    }

    static removeUserInterface() {
        document.getElementsByTagName("main")[0].style.padding = "10px";
    }

    static getWidth() {
        return window.innerWidth;
    }

    static getHeight() {
        return window.innerHeight-70;
    }
}
