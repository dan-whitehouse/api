package org.ricone.api.xPress.request.xCourse;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("courseDAO")
public class CourseDAO extends AbstractDAO<Integer, Course> implements ICourseDAO {
    private final String PRIMARY_KEY = "courseRefId";

    @Override
    public List<CourseWrapper> findAll(MetaData metaData) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join("courseIdentifiers", JoinType.LEFT);
        final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join("courseGrades", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(CourseWrapper.class, lea.get("leaId"), from));
        select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<CourseWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<CourseWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getCourse().getCourseIdentifiers());
            Hibernate.initialize(wrapper.getCourse().getCourseGrades());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<CourseWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join("courseIdentifiers", JoinType.LEFT);
        final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join("courseGrades", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(CourseWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(lea.get("leaRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<CourseWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<CourseWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getCourse().getCourseIdentifiers());
            Hibernate.initialize(wrapper.getCourse().getCourseGrades());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<CourseWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join("courseIdentifiers", JoinType.LEFT);
        final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join("courseGrades", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(CourseWrapper.class, lea.get("leaId"), from));
        select.where(cb.equal(school.get("schoolRefId"), refId));
        select.where(cb.and(cb.equal(lea.get("leaRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<CourseWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<CourseWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getCourse().getCourseIdentifiers());
            Hibernate.initialize(wrapper.getCourse().getCourseGrades());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<CourseWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join("courseIdentifiers", JoinType.LEFT);
        final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join("courseGrades", JoinType.LEFT);
        final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) from.<Course, CourseSection>join("courseSections", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(CourseWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(courseSections.get("courseSectionRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<CourseWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<CourseWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getCourse().getCourseIdentifiers());
            Hibernate.initialize(wrapper.getCourse().getCourseGrades());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<CourseWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join("courseIdentifiers", JoinType.LEFT);
        final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join("courseGrades", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(CourseWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(from.get(PRIMARY_KEY).in(refIds), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        Query<CourseWrapper> q = getSession().createQuery(select);
        List<CourseWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getCourse().getCourseIdentifiers());
            Hibernate.initialize(wrapper.getCourse().getCourseGrades());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public CourseWrapper findByRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join("courseIdentifiers", JoinType.LEFT);
        final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join("courseGrades", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(CourseWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(from.get(PRIMARY_KEY), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<CourseWrapper> q = getSession().createQuery(select);
        CourseWrapper instance = q.getSingleResult();
        Hibernate.initialize(instance.getCourse().getCourseIdentifiers());
        Hibernate.initialize(instance.getCourse().getCourseGrades());
        return instance;
    }


    @Override
    public void save(Course instance) {
        super.persist(instance);
    }

    @Override
    public void update(Course instance) {
        super.saveOrUpdate(instance);
    }

    @Override
    public void delete(Course instance) {
        super.delete(instance);
    }
}


