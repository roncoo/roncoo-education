/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.base;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * 
 * @author wujing
 * @param <T>
 */
public class Page<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = -5764853545343945831L;

	/**
	 * 当前分页的数据集
	 */
	private List<T> list = null;

	/**
	 * 总记录数
	 */
	private int totalCount = 0;

	/**
	 * 总页数
	 */
	private int totalPage = 0;

	/**
	 * 当前页
	 */
	private int pageCurrent = 1;

	/**
	 * 每页记录数
	 */
	private int pageSize = 20;

	/**
	 * 默认构造函数
	 */
	public Page() {
	}

	/**
	 * 构造函数
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param totalPage
	 *            总页数
	 * @param pageCurrent
	 * @param pageSize
	 * @param list
	 */
	public Page(int totalCount, int totalPage, int pageCurrent, int pageSize, List<T> list) {
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Page [list=" + list + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", pageCurrent=" + pageCurrent + ", pageSize=" + pageSize + "]";
	}

}
