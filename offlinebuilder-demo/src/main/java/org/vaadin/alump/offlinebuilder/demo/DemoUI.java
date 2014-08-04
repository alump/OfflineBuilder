/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.vaadin.alump.offlinebuilder.*;

import javax.servlet.annotation.WebServlet;

/**
 * Created by alump on 09/06/14.
 */
@Title("Offline demo")
@Theme("demo")
public class DemoUI extends UI {

    @WebServlet(value = "/*")
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.alump.offlinebuilder.demo.OfflineBuilderDemoWidgetset")
    public static class DemoUIServlet extends OfflineServlet {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        OfflineUIExtension offlineExtension = OfflineUIExtension.get(this);

        // As Panel is half legacy component, using OfflineCssLayout to make content scrollable
        OfflineCssLayout panel = new OfflineCssLayout();
        panel.addStyleName("scrollable-wrapper");
        panel.setSizeFull();
        setContent(panel);
        offlineExtension.setOfflineRoot(panel);

        VerticalLayout layout = new OfflineVerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addStyleName("main-layout");
        panel.addComponent(layout);

        Label header = new OfflineLabel("OfflineBuilder");
        header.addStyleName("header-label");
        layout.addComponent(header);

        Label info = new OfflineLabel("OfflineBuilder allows to define offline UI without need of writing GWT" +
                " code. Add-on offers offline extended versions of basic Vaadin UI components and layouts. This way" +
                " your server defined UI can be used on offline mode too.");
        info.addStyleName("info-label");
        layout.addComponent(info);

        info = new OfflineLabel("To test offline functionality. Turn on flight mode on your device or just disconnect" +
                " from your network connection. Offline UI should look identical with online UI.");
        info.addStyleName("info-label");
        layout.addComponent(info);

        Image image = new OfflineImage();
        image.setWidth("300px");
        image.addStyleName("extra-stylename-image");
        image.setSource(new ThemeResource("img/offline.png"));
        image.setDescription("Example image");
        layout.addComponent(image);

        HorizontalLayout buttonLayout = new OfflineHorizontalLayout();
        buttonLayout.setSpacing(true);
        layout.addComponent(buttonLayout);

        OfflineButton pingButton = new OfflineButton("Ping server");
        pingButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Notification.show("Server says pong!");
            }
        });
        pingButton.setDescription("Button that will be automatically disabled on offline mode");
        buttonLayout.addComponent(pingButton);

        OfflineButton writeButton = new OfflineButton("Store value (TODO)");
        writeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Notification.show("No need to store, in online mode.");
            }
        });
        buttonLayout.addComponent(writeButton);

        /*
        OfflineFormLayout formTest = new OfflineFormLayout();
        OfflineCheckBox checkbox = new OfflineCheckBox("Flag me");
        checkbox.setOfflineValueKey("flag");
        formTest.addComponent(checkbox);
        layout.addComponent(formTest);
        */

        Link link = new OfflineLink("Open project's GitHub page", new ExternalResource("https://github.com/alump/OfflineBuilder"));
        link.addStyleName("github-link");
        layout.addComponent(link);
    }

}
