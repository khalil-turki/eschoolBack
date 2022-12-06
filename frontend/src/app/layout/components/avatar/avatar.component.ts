import {Component, Input, OnInit} from '@angular/core';
import {User, Role} from "../../../auth/models";

@Component({
    selector: 'app-initials-avatar',
    templateUrl: './avatar.component.html',
    styleUrls: ['./avatar.component.scss']
})
export class AvatarComponent implements OnInit {
    public user: User;
    @Input() size;
    public Role = Role;

    constructor() {
        debugger;
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        console.log("etudiant!" + this.user.role === Role.Admin);
    }

    ngOnInit(): void {
    }

    get canvasSize(){
        return this.size;
    }

    get initialsSize(){
        return this.size * 0.4
    }
}
