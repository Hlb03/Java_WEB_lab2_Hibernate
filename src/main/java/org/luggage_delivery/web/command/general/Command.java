package org.luggage_delivery.web.command.general;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException;
}
