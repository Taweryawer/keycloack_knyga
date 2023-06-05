package dao.impl;

import dao.RoleDao;
import domain.Role;
import domain.Role_;
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
public class HibernateRoleDao implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Role role) {
        try {
            entityManager.persist(role);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Role role) {
        try {
            entityManager.merge(role);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Role role) {
        try {
            entityManager.remove(entityManager.getReference(Role.class, role.getId()));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Role> findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where name = :name", Role.class);

        try {
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Role> findById(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> cq = cb.createQuery(Role.class);
            Root<Role> root = cq.from(Role.class);

            cq.select(root).where(cb.equal(root.get(Role_.ID), id));
            TypedQuery<Role> query = entityManager.createQuery(cq);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
