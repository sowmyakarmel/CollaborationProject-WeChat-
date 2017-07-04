package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao 
{
@Autowired
SessionFactory sessionFactory;

public void setSessionFactory(SessionFactory sessionFactory) {
   this.sessionFactory = sessionFactory;
}

public void addBlog(Blog blog) {
blog.setStatus("New");
Session session=sessionFactory.openSession();
Transaction tx = session.beginTransaction();
session.save(blog);
tx.commit();
}

public void updateBlog(Blog blog) {
Session session=sessionFactory.openSession();
session.saveOrUpdate(blog);
}

public void deleteBlog(Blog blog) {
Session session=sessionFactory.openSession();
session.delete(blog);
}

public Blog getBlogId(long blogId) {
Session session=sessionFactory.openSession();
Blog blog=(Blog)session.createQuery("from Blog where blogId="+blogId).getSingleResult();
return blog;


}

@SuppressWarnings("unchecked")
public List<Blog> listBlogs() {
Session session=sessionFactory.openSession();
Transaction tx=session.beginTransaction();
List<Blog> blogs=session.createQuery("from Blog").getResultList();
tx.commit();
return blogs;

}

@SuppressWarnings("unchecked")
public List<Blog> listNewBlogs() {
Session session=sessionFactory.openSession();
List<Blog> blogs=session.createQuery("from Blog where status='New'").getResultList();
return blogs;
}

}
