package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
   public User getUserByCar(String model, int series) {
      String hql = String.format("from User where User.car.model = %s and User.car.series = %s",model,series);
//
//      Session session = sessionFactory.getCurrentSession();
//
//      Query query = (Query) session.createQuery(hql, User.class).getSingleResult();
//      User user = (User) query.getSingleResult();


      Session session = sessionFactory.getCurrentSession();

      User user = session.createQuery(hql, User.class).setParameter(model,series).uniqueResult();
      return user;



   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
