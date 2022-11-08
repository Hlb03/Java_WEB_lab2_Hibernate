package org.luggage_delivery;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 10:42
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.*;
import org.luggage_delivery.dao.dao_interfaces.*;
import org.luggage_delivery.entities.*;
import org.luggage_delivery.session_factory_config.HibernateUtil;

import java.math.BigDecimal;
import java.sql.Date;

public class Starter {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        DeliveryDAO deliveryDAO = new DeliveryDAOImpl(session);
        DeliveryStatusDAO deliveryStatusDAO = new DeliveryStatusDAOImpl(session);
        RouteDAO routeDAO = new RouteDAOImpl(session);
        UserDAO userDAO = new UserDAOImpl(session);
        RoleDAO roleDAO = new RoleDAOImpl(session);

        System.out.println(roleDAO.getAllRoles() + " BEFORE ADDING");
        roleDAO.addNewRole(new Role("TEST_CASE"));
        roleDAO.addNewRole(new Role("ANOTHER_ONE"));

        Role role = roleDAO.getByName("TEST_CASE");
        role.setRoleName("__CASE__");
        roleDAO.updateRole(role.getId(), role);
        System.out.println(roleDAO.getAllRoles() + " AFTER TWICE ADDING AND UPDATING");

//        deliveryStatusDAO.addNewDeliveryStatus(new DeliveryStatus("PROCESSING"));
//        DeliveryStatus status = deliveryStatusDAO.getByStatusName("PROCESSING");
//        Role role = roleDAO.getByName("USER");
//
//        routeDAO.addNewRoute(new Route("Kyiv", "Rivne", new BigDecimal("280.14")));
//        routeDAO.addNewRoute(new Route("Odesa", "Lviv", new BigDecimal("618.42")));
        Route route1 = routeDAO.getByRoute("Kyiv", "Rivne");
//        Route route2 = routeDAO.getByRoute("Odesa", "Lviv");
//
//        userDAO.addNewUser(new User("Peter", "Leam", "pleam@gmail.com", "3223", new BigDecimal("0"), role));
//        User user = userDAO.getByLogin("pleam@gmail.com");
//
//        deliveryDAO.addNewDelivery(new Delivery(new BigDecimal("52.11"), new BigDecimal("209.30"), "FOOD",
//                new BigDecimal("4"), Date.valueOf("2022-10-23"), Date.valueOf("2022-10-26"),
//                "str. BigBen 20/3", status, route1, user));
//        deliveryDAO.addNewDelivery(new Delivery(new BigDecimal("130.59"), new BigDecimal("404.21"), "SPORT",
//                new BigDecimal("7.6"), Date.valueOf("2022-10-23"), Date.valueOf("2022-10-26"),
//                "str. Golden-State 12", status, route2, user));

        System.out.println(deliveryDAO.getByDeliveryDate(Date.valueOf("2022-10-26")));
        System.out.println(deliveryDAO.getByRoute(route1));
        session.getTransaction().commit();
        session.close();
    }
}
