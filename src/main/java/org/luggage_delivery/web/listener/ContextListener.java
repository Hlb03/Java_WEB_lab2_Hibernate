package org.luggage_delivery.web.listener;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 15:46
*/

import org.luggage_delivery.web.command.container.CommandContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

//    private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        LOG.debug("Context initialization was started");
        ServletContext context = sce.getServletContext();
        initCommandContainer(context);
    }

    private void initCommandContainer(ServletContext context) {
        //init all created command and put 'em into container

        CommandContainer container = new CommandContainer();

        context.setAttribute("commandContainer", container);
//        LOG.trace("Set attribute 'commandContainer' to context - " + container);
    }
}
