package com;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class PagedAction extends ActionSupport {

	private static final long serialVersionUID = -5988620023728972001L;

	protected int m_totalPages;

	protected int m_index = 1;

	protected static final int SIZE = Constrants.PAGE_SIZE;

	public int getIndex() {
		return m_index;
	}

	public List<Integer> getPageIndexs() {
		List<Integer> indexs = new ArrayList<Integer>();
		int start = m_index - Constrants.HALF_SIZE - 1;
		int end = m_index + Constrants.HALF_SIZE;

		if (start < 1) {
			start = 1;
			end = 2 + 2 * Constrants.HALF_SIZE;
		} else if (end > m_totalPages) {
			end = m_totalPages;
			start = m_totalPages - Constrants.HALF_SIZE * 2 - 1;
		}

		for (; start <= end; start++) {
			indexs.add(start);
		}
		return indexs;
	}

	public int getTotalPages() {
		return m_totalPages;
	}

	public void setIndex(int index) {
		m_index = index;
	}
}
