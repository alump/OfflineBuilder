package org.vaadin.alump.offlinebuilder.demo.gwt;

import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.Shardable;
import com.vaadin.addon.touchkit.gwt.CacheManifestLinker;

/**
 * Created by alump on 07/07/14.
 */
@LinkerOrder(LinkerOrder.Order.POST)
@Shardable
public class DemoCacheManifestLinker extends CacheManifestLinker {

    public DemoCacheManifestLinker() {
        super();

        // Also include css url that is compiled ondemand
        addCachedResource("../../../VAADIN/themes/demo/styles.css");
    }
}
