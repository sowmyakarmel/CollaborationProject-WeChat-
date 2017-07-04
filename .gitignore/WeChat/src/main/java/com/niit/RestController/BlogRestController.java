package com.niit.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDao;
import com.niit.dao.RegistrationDao;
import com.niit.model.Blog;
import com.niit.model.RegistrationPage;

@RestController
public class BlogRestController 
{

	@Autowired
	BlogDao blogDao;
	
	
	@Autowired
	RegistrationDao registrationDao;
	
	//===========Retrieve All Blogs===================
			
	@GetMapping(value="/blog/")
	public ResponseEntity<List<Blog>> listAllBlogs(){
		List<Blog>blogs=blogDao.listBlogs();
		if(blogs.isEmpty()){
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
	}
	
	
	//===========Retrieve All New  Blogs================
	@GetMapping(value="/blog/new")
	public ResponseEntity<List<Blog>> listAllNewBlogs(){
	List<Blog>blogs=blogDao.listNewBlogs();
	if(blogs.isEmpty()){
		return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
	}
	
	//============Retrieve single Blog================
	@GetMapping(value="/blog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Blog>getBlog(@PathVariable("id")long id){
		Blog blog=blogDao.getBlogId(id);
		if(blog==null)
		{
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	
	//===============Create a Blog=============
	
	@PostMapping(value="/blog/{id}")
	public ResponseEntity<Void>createBlog(@RequestBody Blog blog,@PathVariable Integer id)
	{
		RegistrationPage user= registrationDao.getUserId(id);
		blog.setUser(user);
		blogDao.addBlog(blog);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	//=============Update a User==================
	@PutMapping(value="/blog/{id}")
	public ResponseEntity<Blog>updateBlog(@PathVariable("id")long id,@RequestBody Blog blog){
		Blog currentBlog=blogDao.getBlogId(id);
		if(currentBlog==null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		currentBlog.setTitle(blog.getTitle());
		currentBlog.setDescription(blog.getDescription());
		blogDao.updateBlog(currentBlog);
		return new ResponseEntity<Blog>(currentBlog, HttpStatus.OK);
	}
	
	
	//=====================Delete a blog=================================
	@DeleteMapping(value="/blog/{id}")
	public ResponseEntity<Blog>deleteBlog(@PathVariable("id")long id){
		Blog blog=blogDao.getBlogId(id);
		if(blog==null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		blogDao.deleteBlog(blog);
		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
	}
	
	
	//===============Appprove Blog========================
	@PostMapping(value="/approveblog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>>approveBlog(@PathVariable("id")long id){
		Blog blog=blogDao.getBlogId(id);
		if(blog==null){
			return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
		}
		blog.setStatus("Approved");
		blogDao.updateBlog(blog);
		return new ResponseEntity<List<Blog>>(blogDao.listBlogs(),HttpStatus.OK);
	}
	
	
	//========Reject Blog===================
	@PostMapping(value="/rejectblog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>>rejectBlog(@PathVariable("id")long id){
	Blog blog=blogDao.getBlogId(id);
		if(blog==null)
	{
		return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
	}
	blog.setStatus("Rejected");
	blogDao.updateBlog(blog);
	return new ResponseEntity<List<Blog>>(blogDao.listBlogs(),HttpStatus.OK);
	}
	}
	
	
