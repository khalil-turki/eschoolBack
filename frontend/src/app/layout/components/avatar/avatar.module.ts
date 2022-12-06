import { NgModule } from '@angular/core';

import { CoreCommonModule } from '@core/common.module';

import {AvatarComponent} from "./avatar.component";


@NgModule({
  declarations: [AvatarComponent],
  exports: [
    AvatarComponent
  ],
  imports: [CoreCommonModule]
})
export class AvatarModule {}
