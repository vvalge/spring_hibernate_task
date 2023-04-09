package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUsersByCar(String model, String series) {
        String hql = "select user from User as user join fetch user.car as car where car.model=:model and car.series=:series";
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery(hql);
        query.setParameter("model", "Car2");
        query.setParameter("series", "series2");
        return query.getResultList();
    }

    @Override
    public void showUser(User user) {
        System.out.println("Id = "+user.getId());
        System.out.println("First Name = "+user.getFirstName());
        System.out.println("Last Name = "+user.getLastName());
        System.out.println("Email = "+user.getEmail());
        System.out.println("Car = "+user.getCar());
    }

}
