import {Component, Input, OnInit} from '@angular/core';
import {User, Role} from "../../../auth/models";

@Component({
    selector: 'app-initials-avatar',
    templateUrl: './avatar.component.html',
    styleUrls: ['./avatar.component.scss']
})
export class AvatarComponent implements OnInit {
    @Input() public user: User;
    @Input() size;
    public Role = Role;

    ngOnInit(): void {
    }

    get canvasSize(){
        return this.size;
    }

    get initialsSize(){
        return this.size * 0.4
    }
}
