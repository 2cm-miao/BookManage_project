package org.action;

import java.util.List;
import java.util.Map;

import org.dao.BookDao;
import org.dao.LendDao;
import org.dao.StudentDao;
import org.model.Book;
import org.model.Lend;
import org.model.Pager;
import org.model.Student;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LendAction extends ActionSupport{
	private int pageNow=1;
	private int pageSize=4;
	private Lend lend;
	private String message;
	
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Lend getLend() {
		return lend;
	}
	public void setLend(Lend lend) {
		this.lend = lend;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	LendDao lendDao=new LendDao();
	public String selectAllLend(){
		//判断输入的借书证号是否为空
		if(lend.getReaderId()==null||lend.getReaderId().equals("")){
			this.setMessage("请输入借书证号");;
			return SUCCESS;
		}else if(new StudentDao().selectByReaderId(lend.getReaderId())==null){
			//调用StudentDao中的查询学生的方法，如果为null就表示输入的借书证号不存在
			this.setMessage("不存在该学生");
			return SUCCESS;
		}
		
		//调用LendDao的查询已借阅图书方法，查询，这里用到了分页查询
		List list=lendDao.selectLend(lend.getReaderId(),this.getPageNow(),this.getPageNow());
		
		//根据当前页及一共多少条记录创建分页的类Pager对象
		Pager page=new Pager(pageNow,lendDao.selectLendSize(lend.getReaderId()));
		Map request=(Map)ActionContext.getContext().get("resquest");
		request.put("list",list);
		request.put("page",page);
		request.put("readerId", lend.getReaderId());
		return SUCCESS;
	}
	
	public String lendBook() throws Exception{
		BookDao bookDao=new BookDao();
		//如果ISBN为空或者不存在该ISBN的书，就返回到原来的情况，只是多了提示信息
		if(lend.getISBN()==null||lend.getISBN().equals("")||bookDao.selectBook(lend.getISBN())==null||
				(bookDao.selectBook(lend.getISBN()).getSnum()<1)){
			List list=lendDao.selectLend(lend.getReaderId(), this.getPageNow(), this.getPageSize());
			
			Pager page=new Pager(pageNow,lendDao.selectLendSize(lend.getReaderId()));
			
			Map request=(Map)ActionContext.getContext().get("request");
			request.put("list", list);
			request.put("page", page);
			request.put("readerId", lend.getReaderId());
			setMessage("IBSN不能为空或者不存在该ISBN的图书或者该ISBN的图书没有库存量！");
			return SUCCESS;
		}else if(lend.getBookId()==null||lend.getBookId().equals("")||
				lendDao.selectByBookId(lend.getBookId())!=null){
			//如果输入的图书ID为空或该图书ID已经存在也返回到原来的情况，并给出提示信息
			List list=lendDao.selectLend(lend.getReaderId(), this.getPageNow(), this.getPageSize());
			
			Pager page=new Pager(pageNow,lendDao.selectLendSize(lend.getReaderId()));
			
			Map request=(Map)ActionContext.getContext().get("request");
			request.put("list", list);
			request.put("page", page);
			request.put("readerId",lend.getReaderId());
			this.setMessage("该图书ID已经存在或图书ID为空");
			return SUCCESS;
		}
		
		Lend l=new Lend();
		l.setBookId(lend.getBookId());
		l.setISBN(lend.getISBN());
		l.setReaderId(lend.getReaderId());
		l.setlTime(lend.getlTime());
		lendDao.addLend(l);
		
		Book book=bookDao.selectBook(lend.getISBN());
		book.setSnum(book.getSnum()-1);;
		bookDao.updateBook(book);
		
		StudentDao studentDao=new StudentDao();
		Student stu=studentDao.selectByReaderId(lend.getReaderId());
		stu.setNum(stu.getNum()+1);  //设置学生的借书量+1
		studentDao.updateStudent(stu);
		
		List list=lendDao.selectLend(lend.getBookId(), this.getPageNow(), this.getPageSize());
		
		Pager page=new Pager(pageNow,lendDao.selectLendSize(lend.getReaderId()));
		
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("list", list);
		request.put("page", page);
		request.put("readerId", lend.getReaderId());
		
		return SUCCESS;
	}
}
