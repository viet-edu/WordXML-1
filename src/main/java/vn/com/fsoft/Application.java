package vn.com.fsoft;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    private static final int DEFAULT_PORT = 8000;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";

    public static void main(String[] args) throws Exception {
        new Application()
                .startJetty(args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]));
    }

    private void startJetty(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
        log.info("Server started at port {}", port);
        server.join();
    }

    private static ServletContextHandler getServletContextHandler(
            WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)),
                MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
        return contextHandler;
    }

    private static WebApplicationContext getContext() throws ClassNotFoundException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        final Properties profileProperties = getStartupProperties();
        context.register(Class.forName(profileProperties.getProperty("startup.config")));
        String[] activeProfiles = profileProperties.get("profiles").toString().split(" ");
        context.getEnvironment().setActiveProfiles(activeProfiles);
        return context;
    }

    private static Properties getStartupProperties() {
        final Properties profileProperties = new Properties();
        final InputStream propertiesStream = Application.class
                .getResourceAsStream("/application.properties");
        try {
            profileProperties.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Registered profils named '"
                + profileProperties.getProperty("profiles").toString() + "' are set active.");
        log.info("Startup config class: " + profileProperties.getProperty("startup.config").toString());
        return profileProperties;
    }
}
