package org.luggage_delivery.dao.dao_implementations;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 21:58
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_interfaces.DeliveryDAO;
import org.luggage_delivery.entities.Delivery;
import org.luggage_delivery.entities.DeliveryStatus;
import org.luggage_delivery.entities.Route;
import org.luggage_delivery.util.UpdateUtil;

import java.sql.Date;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {

    private final Session session;

    public DeliveryDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addNewDelivery(Delivery delivery) {
        session.save(delivery);
    }

    @Override
    public List<Delivery> getAll() {
        return session.createQuery("SELECT dl FROM Delivery dl", Delivery.class).list();
    }

    @Override
    public List<Delivery> getDeliveryByStatus(DeliveryStatus status) {
        return session.createQuery("SELECT dl FROM Delivery dl WHERE dl.deliveryStatus = :status", Delivery.class)
                .setParameter("status", status)
                .list();
    }

    @Override
    public List<Delivery> getByDeliveryDate(Date deliveryDate) {
        return session.createQuery("SELECT dl FROM Delivery dl WHERE dl.deliveryDate = :deliveryDate", Delivery.class)
                .setParameter("deliveryDate", deliveryDate)
                .list();
    }

    @Override
    public List<Delivery> getByRoute(Route route) {
        return session.createQuery("SELECT dl FROM Delivery dl WHERE dl.route = :route", Delivery.class)
                .setParameter("route", route)
                .list();
    }

    @Override
    public Delivery getById(int id) {
        return session.get(Delivery.class, id);
    }

    @Override
    public void updateDelivery(int id, Delivery delivery) {
        Delivery delivery1 = getById(id);
        UpdateUtil.updateDeliveryParams(delivery1, delivery);
        session.update(delivery1);
    }

    @Override
    public void deleteDelivery(Delivery delivery) {
        session.createQuery("DELETE FROM Delivery dt WHERE dt.size = :size AND dt.totalPrice = :price AND " +
                "dt.luggageType = :type AND dt.weight = :weight AND dt.startDate = :startDate AND dt.deliveryDate = :delDate AND" +
                " dt.deliveryAddress = :address AND dt.deliveryStatus = :status AND dt.route = :route AND dt.user = :user")
                .setParameter("size", delivery.getSize())
                .setParameter("price", delivery.getTotalPrice())
                .setParameter("type", delivery.getLuggageType())
                .setParameter("weight", delivery.getWeight())
                .setParameter("startDate", delivery.getStartDate())
                .setParameter("delDate", delivery.getDeliveryDate())
                .setParameter("address", delivery.getDeliveryAddress())
                .setParameter("status", delivery.getDeliveryStatus())
                .setParameter("route", delivery.getRoute())
                .setParameter("user", delivery.getUser())
                .executeUpdate();
    }
}
