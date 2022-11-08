package org.luggage_delivery.web.serlvet;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 15:46
*/

import org.luggage_delivery.web.command.container.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class FrontServlet extends HttpServlet {

    private CommandContainer container;

    @Override
    public void init() throws ServletException {
        container = (CommandContainer) getServletContext().getAttribute("commandContainer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello world!");
        super.doGet(req, resp);
    }
}
