import {Component, Input, OnInit} from '@angular/core';
import {ClasseDto} from "../../../../gs-api/src/models/classe-dto";
import {ClasseControllerService} from "../../../../gs-api/src/services/classe-controller.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.scss']
})
export class ClassesComponent implements OnInit {
  listClasses : Array<ClasseDto> = [];
  @Input()
  classeDto: ClasseDto = {};

  constructor(
      private router : Router,
      private classeService: ClasseControllerService

  ) { }

  ngOnInit(): void {
    this.findAllClasses();
  }

  findAllClasses(): void {
    this.classeService.findAllUsingGET1().subscribe(res => {
      this.listClasses = res;
    });
  }


}
