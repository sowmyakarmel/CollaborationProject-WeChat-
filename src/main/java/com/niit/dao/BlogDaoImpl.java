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
sessionFactory.getCurrentSession().saveOrUpdate(blog);



}

public void updateBlog(Blog blog) {
Session session=sessionFactory.getCurrentSession();
session.saveOrUpdate(blog);
}

public void deleteBlog(Blog blog) {
Session session=sessionFactory.getCurrentSession();
session.delete(blog);
}

public Blog getBlogId(long blogId) {
Session session=sessionFactory.getCurrentSession();
Blog blog=(Blog)session.createQuery("from Blog where blogId="+blogId).getSingleResult();
return blog;


}

@SuppressWarnings("unchecked")
public List<Blog> listBlogs() {
List<Blog> blogs=sessionFactory.getCurrentSession().createQuery("from Blog").getResultList();


return blogs;

}

@SuppressWarnings("unchecked")
public List<Blog> listNewBlogs() {
Session session=sessionFactory.getCurrentSession();
List<Blog> blogs=session.createQuery("from Blog where status='New'").getResultList();
return blogs;
}

}
