package com.bus.demo.dto;

import java.util.List;

import com.bus.demo.entity.GetInfor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    int pageCount;
    T response;
	public int getPageCount() {
		return pageCount;
	}
	public APIResponse(int pageCount, T response) {
		super();
		this.pageCount = pageCount;
		this.response = response;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}

}
