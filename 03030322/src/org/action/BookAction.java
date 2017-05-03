package org.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.struts2.ServletActionContext;
import org.dao.BookDao;
import org.dao.LendDao;
import org.model.Book;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport{
	private String message;
	private File photo;
	private Book book;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	BookDao bookDao=new BookDao();
	
	public String addBook() throws Exception{
		Book bo=bookDao.selectBook(book.getISBN());
		if(bo!=null){                      //判断要添加的图书是否已经存在
			this.setMessage("ISBN已经存在");
			return SUCCESS;
		}
		
		Book b=new Book();
		b.setISBN(book.getISBN());
		b.setBookName(book.getBookName());
		b.setAuthor(book.getAuthor());
		b.setPublisher(book.getPublisher());
		b.setPrice(book.getPrice());
		b.setCnum(book.getCnum());
		b.setSnum(book.getSnum());
		b.setSummary(book.getSummary());
		
		if(this.getPhoto()!=null){
			FileInputStream fis=new FileInputStream(this.getPhoto());
			byte[] buffer=new byte[fis.available()];
			fis.read(buffer);
			b.setPhoto(buffer);
		}
		
		bookDao.addBook(b);
		this.setMessage("添加成功！");
		
		return SUCCESS;
	}
	
	public String deleteBook() throws Exception{
		if(new LendDao().selectByBookISBN(book.getISBN())!=null){
			this.setMessage("该图书已经被借出，不能删除");
			return SUCCESS;
		}
		
		Book bo=bookDao.selectBook(book.getISBN());
		if(bo==null){            //首先判断是否存在该图书
			this.setMessage("要删除的图书不存在！");
			return SUCCESS;
		}else if(new LendDao().selectByBookISBN(book.getISBN())!=null){
			this.setMessage("该图书已经被借出，故不能删除图书信息！");
			return SUCCESS;
		}
		
		bookDao.deleteBook(book.getISBN());
		this.setMessage("删除成功！");
		return SUCCESS;
	}
	
	public String selectBook() throws Exception{
		Book onebook=bookDao.selectBook(book.getISBN());
		if(onebook==null){
			this.setMessage("不存在该图书!");
			return SUCCESS;
		}
		
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("onebook", onebook);
		return SUCCESS;
	}
	
	public String getImage() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		String ISBN=book.getISBN();
		Book b=bookDao.selectBook(ISBN);
		byte[] photo=b.getPhoto();
		
		response.setContentType("image/jpeg");
		ServletOutputStream os=response.getOutputStream();
		
		if(photo!=null&&photo.length!=0){
			for(int i=0;i<photo.length;i++){
				os.write(photo[i]);
			}
			os.flush();
		}
		return NONE;
	}
	
	public String updateBook() throws Exception{
		Book b=bookDao.selectBook(book.getISBN());
		if(b==null){
			this.setMessage("要修改的图书不存在，请先查看是否存在该图书！");
			return SUCCESS;
		}
		
		b.setISBN(book.getISBN());
		b.setBookName(book.getBookName());
		b.setAuthor(book.getAuthor());
		b.setPublisher(book.getPublisher());
		b.setPrice(book.getPrice());
		b.setCnum(book.getCnum());
		b.setSnum(book.getSnum());
		b.setSummary(book.getSummary());
		
		if(this.getPhoto()!=null){
			FileInputStream fis=new FileInputStream(this.getPhoto());
			byte[] buffer=new byte[fis.available()];
			fis.read(buffer);
			b.setPhoto(buffer);
		}
		
		bookDao.updateBook(b);
		this.setMessage("修改成功！");
		return SUCCESS;
	}
}
