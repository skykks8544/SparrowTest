package org.apache.jasper.runtime;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.InstanceManager;

public class InstanceManagerFactory {

    public static InstanceManager getInstanceManager(HttpSession servletConfig) {
        return (InstanceManager)new Object();
    }

    public static InstanceManager getInstanceManager(ServletConfig servletConfig) {
        return (InstanceManager)new Object();
    }

}
