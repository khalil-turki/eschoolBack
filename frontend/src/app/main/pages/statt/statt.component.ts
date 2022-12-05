import { Component, OnInit } from '@angular/core';
import {EcoleControllerService} from "../../../../gs-api/src/services/ecole-controller.service";
import {EcoleDto} from "../../../../gs-api/src/models/ecole-dto";
import {colors} from "../../../colors.const";


@Component({
  selector: 'app-statt',
  templateUrl: './statt.component.html',
  styleUrls: ['./statt.component.scss']
})
export class StattComponent implements OnInit {
  private result: Array<EcoleDto>;
  private ecoleStatus: any;
  private ecoleName: any;
  private tooltipShadow = 'rgba(0, 0, 0, 0.25)';
  private primaryColorShade = '#836AF9';
  private yellowColor = '#ffe800';
  private successColorShade = '#28dac6';
  private warningColorShade = '#ffe802';
  private warningLightColor = '#FDAC34';
  private infoColorShade = '#299AFF';
  private greyColor = '#4F5D70';
  private blueColor = '#2c9aff';
  private blueLightColor = '#84D0FF';
  private greyLightColor = '#EDF1F4';
  private lineChartPrimary = '#666ee8';
  private lineChartDanger = '#ff4961';
  private labelColor = '#6e6b7b';
  private grid_line_color = 'rgba(200, 200, 200, 0.2)'; // RGBA color helps in dark layout

  constructor(        private ecoleControllerService: EcoleControllerService,

                      ) { }

  ngOnInit(): void {
    this.ecoleControllerService.findAllUsingGET2().subscribe((res)=>{
      this.result=res;
      this.ecoleStatus = this.result.map((ecoleDto:any) => ecoleDto.photo);
      this.ecoleName = this.result.map((ecoleDto:any) => ecoleDto.nom);

      console.log(this.ecoleStatus,this.ecoleName);
       this.polarAreaChart = {
        chartType: 'polarArea',
        options: {
          responsive: true,
          maintainAspectRatio: false,
          responsiveAnimationDuration: 500,
          legend: {
            position: 'right',
            labels: {
              usePointStyle: true,
              padding: 25,
              boxWidth: 10
            }
          },
          tooltips: {
            // Updated default tooltip UI
            shadowOffsetX: 1,
            shadowOffsetY: 1,
            shadowBlur: 8,
            shadowColor: this.tooltipShadow,
            backgroundColor: colors.solid.white,
            titleFontColor: colors.solid.black,
            bodyFontColor: colors.solid.black
          },
          scale: {
            scaleShowLine: true,
            scaleLineWidth: 1,
            ticks: {
              display: false
            },
            reverse: false,
            gridLines: {
              display: false
            }
          },
          animation: {
            animateRotate: false
          }
        },

        labels: this.ecoleName,
        datasets: [
          {
            label: 'Population (millions)',
            backgroundColor: [
              this.primaryColorShade,
              this.warningColorShade,
              colors.solid.primary,
              this.infoColorShade,
              this.greyColor,
              this.successColorShade
            ],
            data: this.ecoleStatus,
            borderWidth: 0
          }
        ]
      };


    });
  }

  public polarAreaChart = {
    chartType: 'polarArea',
    options: {
      responsive: true,
      maintainAspectRatio: false,
      responsiveAnimationDuration: 500,
      legend: {
        position: 'right',
        labels: {
          usePointStyle: true,
          padding: 25,
          boxWidth: 10
        }
      },
      tooltips: {
        // Updated default tooltip UI
        shadowOffsetX: 1,
        shadowOffsetY: 1,
        shadowBlur: 8,
        shadowColor: this.tooltipShadow,
        backgroundColor: colors.solid.white,
        titleFontColor: colors.solid.black,
        bodyFontColor: colors.solid.black
      },
      scale: {
        scaleShowLine: true,
        scaleLineWidth: 1,
        ticks: {
          display: false
        },
        reverse: false,
        gridLines: {
          display: false
        }
      },
      animation: {
        animateRotate: false
      }
    },

    labels: ['Africa', 'Asia', 'Europe', 'America', 'Antarctica', 'Australia'],
    datasets: [
      {
        label: 'Population (millions)',
        backgroundColor: [
          this.primaryColorShade,
          this.warningColorShade,
          colors.solid.primary,
          this.infoColorShade,
          this.greyColor,
          this.successColorShade
        ],
        data: [19, 17.5, 15, 13.5, 11, 9],
        borderWidth: 0
      }
    ]
  };


}
