package ar.edu.unq.desapp.grupoA.repositories;

import ar.edu.unq.desapp.grupoA.models.UserModel;
import ar.edu.unq.desapp.grupoA.repositories.utils.GenericRepository;
import ar.edu.unq.desapp.grupoA.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;


public class UserModelRepository extends HibernateGenericDAO<UserModel> implements GenericRepository<UserModel> {

    private static final long serialVersionUID = -4036535812105672110L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<UserModel> filterPeople(final String pattern) {
        return (List<UserModel>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<UserModel> doInHibernate(final Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(UserModel.class);
                criteria.add(Restrictions.like("fullName", "%" + pattern + "%"));
                return criteria.list();
            }

        });
    }

    @Override
    protected Class<UserModel> getDomainClass() {
        return UserModel.class;
    }
}