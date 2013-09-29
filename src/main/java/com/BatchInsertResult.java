package com;

import java.util.ArrayList;
import java.util.List;

public class BatchInsertResult {

	public int m_success;

	public int m_fail;

	public List<Integer> m_failLines = new ArrayList<Integer>();

	public void addFail(int line) {
		m_failLines.add(line);
		m_fail++;
	}

	public void addSuccess() {
		m_success++;
	}

	public int getFail() {
		return m_fail;
	}

	public String getFailLines() {
		StringBuilder m_sb = new StringBuilder();

		for (Integer i : m_failLines) {
			m_sb.append(i).append(',');
		}
		return m_sb.toString();
	}

	public int getSuccess() {
		return m_success;
	}

	public void setSuccess(int success) {
		m_success = success;
	}
}
