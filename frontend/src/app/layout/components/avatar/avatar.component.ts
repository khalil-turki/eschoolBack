import {Component, Input, OnInit} from '@angular/core';
import {User, Role} from "../../../auth/models";
import {EtudiantDto} from "../../../../gs-api/src/models/etudiant-dto";

@Component({
    selector: 'app-initials-avatar',
    templateUrl: './avatar.component.html',
    styleUrls: ['./avatar.component.scss']
})
export class AvatarComponent implements OnInit {
    @Input() public user: User|EtudiantDto;
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
