package org.vaadin.alump.offlinebuilder.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.vaadin.alump.offlinebuilder.OfflineCssLayout;
import org.vaadin.alump.offlinebuilder.OfflineLabel;
import org.vaadin.alump.offlinebuilder.OfflineServlet;
import org.vaadin.alump.offlinebuilder.OfflineUIExtension;

import javax.servlet.annotation.WebServlet;

/**
 * Created by alump on 09/06/14.
 */
@Title("Offline demo")
@Theme("demo")
public class DemoUI extends UI {

    @WebServlet(value = "/*")
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.alump.offlinebuilder.demo.gwt.OfflineBuilderDemoWidgetset")
    public static class DemoUIServlet extends OfflineServlet {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        OfflineUIExtension offlineExtension = OfflineUIExtension.get(this);

        OfflineCssLayout layout = new OfflineCssLayout();
        layout.addStyleName("main-layout");
        setContent(layout);
        offlineExtension.setOfflineRoot(layout);

        OfflineLabel label = new OfflineLabel("Hello World!");
        label.addStyleName("extra-stylename");
        layout.addComponent(label);

    }

}
