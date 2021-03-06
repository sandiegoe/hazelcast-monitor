import {Component} from '@angular/core';
import {SharedMasterComponent} from '../shared-master.component';
import {MapsProductDTO} from '@shared/dto/topic-products-aliases.dto';
import {SharedClustersService} from '@shared/services/shared-clusters.service';
import {SharedSnackbarService} from '@shared/services/shared-snackbar.service';
import {SharedHazelcastAgentService} from '@shared/services/shared-hazelcast-agent.service';
import {DistributedObjectType} from '@shared/dto/topics.dto';
import {MapSummaryDTO} from '@shared/dto/topic-products.dto';
import {SharedPageIconsConstants} from '@shared/constants/shared-page-icons.constants';
import {PageDashboardMapStatsComponent} from '../../page-dashboard-map-stats/page-dashboard-map-stats.component';
import {PageDashboardMapComponent} from '../../page-dashboard-map/page-dashboard-map.component';
import {SharedTabsService} from "@shared/services/shared-tabs.service";

@Component({
  templateUrl: './page-dashboard-maps.component.html',
  styleUrls: [ '../shared-master.component.scss' ]
})
export class PageDashboardMapsComponent extends SharedMasterComponent<MapsProductDTO> {
  public constructor(clustersService: SharedClustersService,
                     snackbarService: SharedSnackbarService,
                     hazelcastService: SharedHazelcastAgentService,
                     private tabsService: SharedTabsService) {
    super(DistributedObjectType.MAP, clustersService, snackbarService, hazelcastService);
  }

  get title(): string {
    return `Maps of ${this.clusterName}`;
  }

  public navigateToStats(row: MapSummaryDTO): void {
    const mapName: string = row.name;

    this.tabsService.addTab({
      label: `${mapName} statistics`,
      icon: SharedPageIconsConstants.STATS_ICON,
      componentClass: PageDashboardMapStatsComponent,
      componentInputs: {
        mapName: mapName,
        mapType: DistributedObjectType.MAP
      }
    });
  }

  public navigateToDetails(row: MapSummaryDTO): void {
    const mapName: string = row.name;

    this.tabsService.addTab({
      label: mapName,
      icon: SharedPageIconsConstants.MAPS_ICON,
      componentClass: PageDashboardMapComponent,
      componentInputs: {
        mapName: mapName
      }
    });
  }
}
