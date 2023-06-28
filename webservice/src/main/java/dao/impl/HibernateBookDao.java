package dao.impl;

import dao.BookDao;
import dao.RoleDao;
import domain.Book;
import domain.Role;
import domain.Role_;
import domain.User_;
import exception.DaoException;
import filter.BookFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class HibernateBookDao implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> getById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(Book.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long count(BookFilter bookFilter) {
        try {
            return prepareCountQuery(bookFilter).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findAll(BookFilter bookFilter) {
        try {
            return prepareQuery(bookFilter).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private TypedQuery<Book> prepareQuery(BookFilter bookFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        cq.select(root);
        if (bookFilter.getName() != null) {
            cq.where(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + bookFilter.getName().toUpperCase() + "%"));
        }

        String sortBy = bookFilter.getSortBy();
        Path<Object> sortPath = root.get(sortBy);
        Order order = bookFilter.isAsc() ? criteriaBuilder.asc(sortPath) : criteriaBuilder.desc(sortPath);

        cq.orderBy(order);

        TypedQuery<Book> query = entityManager.createQuery(cq);
        query.setFirstResult((bookFilter.getPageNumber() - 1) * bookFilter.getPageSize());
        query.setMaxResults(bookFilter.getPageSize());

        return query;
    }

    private TypedQuery<Long> prepareCountQuery(BookFilter bookFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
        Root<Book> root = cq.from(Book.class);

        cq.select(criteriaBuilder.count(root));
        if (bookFilter.getName() != null) {
            cq.where(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + bookFilter.getName().toUpperCase() + "%"));
        }

        return entityManager.createQuery(cq);
    }
}
