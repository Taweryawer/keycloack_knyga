package dao.impl;

import dao.UserDao;
import domain.User;
import domain.User_;
import exception.DaoException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(User user) {
        try {
            entityManager.remove(entityManager.getReference(User.class, user.getId()));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root);
            TypedQuery<User> query = entityManager.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root)
                .where(cb.equal(root.get(User_.LOGIN), login));
            TypedQuery<User> query = entityManager.createQuery(cq);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            TypedQuery<User> query = entityManager
                .createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
