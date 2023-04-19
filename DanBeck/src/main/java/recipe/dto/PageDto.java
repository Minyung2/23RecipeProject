package recipe.dto;

public class PageDto {
	private int currentPage;
	private int totalCount;	
	private int pageSize;
	private int totalPage;
	private int startPage;
	private int endPage;

	private int startNo;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public PageDto(int currentPage, int totalCount, int pageSize) {   //외부(비지니스로직)에서 결정하고 전달되는값.
		this.totalCount=totalCount;
		this.pageSize=pageSize;
		

		totalPage = (totalCount-1)/pageSize + 1;

		this.currentPage= (currentPage > totalPage)? totalPage:currentPage;
		this.currentPage= (currentPage < 1)? 1:currentPage;

		startNo=(this.currentPage-1)*pageSize;
		
		startPage = (this.currentPage-1)/10*10+1;		
		endPage = startPage+9;
		endPage = endPage > totalPage ? totalPage:endPage;   
	}
}
