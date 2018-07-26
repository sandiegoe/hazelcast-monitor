import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {PageTestComponent} from './page-test.component';
import {SharedMdcTreeModule} from '@shared/components/mdc-tree/shared-mdc-tree.module';
import {MdcIconModule} from '@angular-mdc/web';
import {SharedMdcTableModule} from '@shared/components/mdc-table/shared-mdc-table.module';
import {SharedMdcPaginatorModule} from '@shared/components/mdc-paginator/shared-mdc-paginator.module';
import {SharedMdcTreeTableModule} from '@shared/components/mdc-treetable/shared-mdc-treetable.module';

@NgModule({
  declarations: [
    PageTestComponent
  ],
  imports: [
    BrowserModule,

    // Angular Web MDC
    MdcIconModule,

    // Shared modules
    SharedMdcTreeModule,
    SharedMdcTableModule,
    SharedMdcPaginatorModule,
    SharedMdcTreeTableModule
  ],
  providers: [
  ]
})
export class PageTestModule { }
