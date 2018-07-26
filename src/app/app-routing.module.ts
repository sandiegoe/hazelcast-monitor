import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {PageClustersComponent} from './page-clusters/page-clusters.component';
import {PageDashboardComponent} from './page-dashboard/page-dashboard.component';
import {PageNotFoundComponent} from './page-notfound/page-notfound.component';
import {SharedClusterGuard} from '@shared/guards/shared-cluster.guard';
import {SharedServicesModule} from '@shared/services/shared-services.module';
import {PageConnectComponent} from './page-connect/page-connect.component';
import {SharedDashboardGuard} from '@shared/guards/shared-dashboard-guard';
import {PageTestComponent} from './page-test/page-test.component';
import {PageDashboardMembersComponent} from './page-dashboard-members/page-dashboard-members.component';
import {PageDashboardMapsComponent} from './page-dashboard-maps/page-dashboard-maps.component';
import {PageDashboardMapComponent} from "./page-dashboard-map/page-dashboard-map.component";

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: 'connect',
        component: PageConnectComponent
      },
      {
        path: 'dashboard',
        component: PageDashboardComponent,
        canActivate: [
          SharedDashboardGuard
        ],
        children: [
          {
            path: 'members',
            component: PageDashboardMembersComponent,
            outlet: 'section'
          },
          {
            path: 'maps',
            component: PageDashboardMapsComponent,
            outlet: 'section'
          },
          {
            path: 'maps/:name',
            component: PageDashboardMapComponent,
            outlet: 'section'
          }
        ]
      },
      {
        path: 'clusters',
        component: PageClustersComponent,
        canActivate: [
          SharedClusterGuard
        ]
      },
      {
        path: 'test',
        component: PageTestComponent
      },
      {
        path: '',
        redirectTo: '/clusters',
        pathMatch: 'full'
      },
      { path: '**', component: PageNotFoundComponent }
    ]),
    SharedServicesModule
  ],
  providers: [
  ]
})
export class AppRoutingModule {
}
