package cn.qdcares.rpc.common.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @描述: 分页组件.
 * @作者: dongqun
 * @创建时间: 2013-7-25,下午11:33:41 .
 * @版本: 1.0 .
 */
public class PageBean<T> implements Serializable{

	private static final long serialVersionUID = 8470697978259453214L;

	private List<T> rows; // 本页的数据列表
	private long total; // 总记录数
	private long totalPage; //总页数


	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}
