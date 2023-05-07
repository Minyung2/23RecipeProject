package com.project.danback.recipeboard.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PageDto {
	private int currentPage; 	//현재페이지
	private int pageSize;		//한 화면에 띄울 게시글 limit 범위
	private int totalCount;		//전체 게시글
	
	//계산에 필요한 값
	private int startNo;		//limit 시작 인덱스
	private int totalPage;		//전체 페이지 개수       처음 <1 2 3> 끝
	private int startPage;		//페이지 안에 띄울 게시글 시작 idx
	private int endPage;		//페이지 안에 띄울 게실글 끝 idx
	
	//검색에 필요한 값
	private String cate1;
	private String cate2;
	
	public PageDto(int currentPage, int totalCount, int pageSize, String cate1, String cate2) {
	    super();
	    this.currentPage = currentPage;
	    this.pageSize = pageSize;
	    this.totalCount = totalCount;
	    totalPage = (totalCount + pageSize - 1) / pageSize;

	    this.currentPage = (currentPage > totalPage || currentPage < 1) ? 1 : currentPage;
	    startNo = (this.currentPage - 1) * pageSize;

	    startPage = ((this.currentPage - 1) / 10) * 10 + 1;
	    endPage = startPage + 9;
	    if (endPage > totalPage) {
	        endPage = totalPage;
	    }

	    this.cate1 = cate1;
	    this.cate2 = cate2;
	}
}
