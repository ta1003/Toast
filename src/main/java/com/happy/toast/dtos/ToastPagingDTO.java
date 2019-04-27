package com.happy.toast.dtos;

public class ToastPagingDTO {
	
	private int pageListSize; 		// ?��?���? 목록?�� ?��?���? (ex) 10개로 ?��?���? <1~10> 5개로 ?��?���? <1~5>) 	
	private int nowPageNo;    		// ?��?�� ?��?���? 번호
	private int totalBoardSize;     // �? 게시�? 개수
	private int pageBoardSize;      // ?��?���??�� 보여�? 게시�? ?���? (ex)5�? ?��?���? 1?��?���??�� 게시�? 5개�?? 보여�?)
	
	private int firstPageNo; 		// 처음 ?��?���? 번호  (만약 ?��?���? 개수�? �? 42개라�? firstPageNo?? 1)
	private int lastPageNo; 		// 마�?�? ?��?���? 번호  (만약 ?��?���? 개수�? �? 42개라�? lastPageNo?? 1)
	
	private int prevPageNo; 		// ?��?�� ?��?���??��?�� ?��?�� ?��?���? 번호
	private int nextPageNo; 		// ?��?�� ?��?���??��?�� ?��?�� ?��?���? 번호
	
	private int startPageNo; 		// ?��?�� ?��?���? 목록?��(ex)<11~20>)?��?�� ?��?�� ?��?���? 번호 
	private int endPageNo;   		// ?��?�� ?��?���? 목록?��(ex)<11~20>)?��?�� 맨끝 ?��?���? 번호
	
	private int firstBoardNo;		// ?��?�� ?��?���??��?�� ?��?�� 게시�? 번호
	private int endBoardNo;			// ?��?�� ?��?���??��?�� 마�?�? 게시�? 번호
	
	
	public ToastPagingDTO() {
		
	}	
	
	public ToastPagingDTO(int pageListSize, int nowPageNo, int totalBoardSize, int pageBoardSize) {		
		this.pageListSize = pageListSize;
		this.nowPageNo = nowPageNo;		
		this.pageBoardSize = pageBoardSize;
		
		setTotalBoardSize(totalBoardSize);
	}



	public int getPageListSize() {
		return pageListSize;
	}
	public void setPageListSize(int pageListSize) {
		this.pageListSize = pageListSize;
	}
	public int getNowPageNo() {
		return nowPageNo;
	}
	public void setNowPageNo(int nowPageNo) {
		this.nowPageNo = nowPageNo;
	}
	public int getTotalBoardSize() {
		return totalBoardSize;
	}
	public void setTotalBoardSize(int totalBoardSize) {
		this.totalBoardSize = totalBoardSize;
		makePage();
	}
	public int getPageBoardSize() {
		return pageBoardSize;
	}
	public void setPageBoardSize(int pageBoardSize) {
		this.pageBoardSize = pageBoardSize;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getLastPageNo() {
		return lastPageNo;
	}
	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}
	public int getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	public int getFirstBoardNo() {
		return firstBoardNo;
	}
	public void setFirstBoardNo(int firstBoardNo) {
		this.firstBoardNo = firstBoardNo;
	}
	public int getEndBoardNo() {
		return endBoardNo;
	}
	public void setEndBoardNo(int endBoardNo) {
		this.endBoardNo = endBoardNo;
	}
	
	
	private void makePage() {
		if(totalBoardSize == 0) {
			return;
		}
		if(nowPageNo == 0) {
			setNowPageNo(1);
		}
		if(pageListSize == 0) {
			setPageListSize(10);
		}
		if(pageBoardSize == 0) {
			setPageBoardSize(10);
		}
		
		int finalPage = (totalBoardSize+(pageBoardSize-1))/pageBoardSize; // 마�?�? ?��?���? 번호
		
		// ?��?�� ?��?���?�? 마�?�? ?��?���?보다 ?��경우 ?��?�� ?��?���?�? 마�?�? ?��?���?�? ?��?��
		if(nowPageNo > finalPage) {
			setNowPageNo(finalPage);			
		}
		
		boolean isPageNowFirst = (nowPageNo == 1)? true:false; 			// ?��?�� ?��?���?�? 맨처?��?���?
		boolean isPageNowLast = (nowPageNo == finalPage)? true:false;	// ?��?�� ?��?���?�? 마�?막인�?
		
		// ?��?�� ?��?���? 번호?��?�� 보여�? ?��?���? 리스?�� 번호 (ex) ?��?�� ?��?���?�? 8?��?���? <6~10>)
		int startPage = ((nowPageNo-1)/pageListSize)*pageListSize+1;
		int endPage = startPage+(pageListSize-1);
		
		// ?��?�� ?��?���? 번호?��?�� 보여�? 게시�? 번호?�� (ex) ?��?�� ?��?���?�? 8?��?���? 71~80개의 게시�?)
		int startBoard = nowPageNo*pageBoardSize-(pageBoardSize-1);
		int endBoard = nowPageNo*pageBoardSize;
		
		// endPage�? 마�?�? ?��?���?�? ?��?��?�� ?��?���?
		if(endPage > finalPage) {
			endPage = finalPage;
		}
		
		// ?��?�� ?��?���?�? 첫번째페?���??���? 
		if(isPageNowFirst) {
			setPrevPageNo(1);
		}else {
			setPrevPageNo((nowPageNo-1)<0? 1:(nowPageNo-1));
		}
		// ?��?���? ?��???��,?��?�� 번호
		setStartPageNo(startPage);
		setEndPageNo(endPage);
		// 게시�? ?��???��,?��?�� 번호 
		setFirstBoardNo(startBoard);
		setEndBoardNo(endBoard);
		// ?��?�� ?��?���?�? 마�?막페?���??���?
		if(isPageNowLast) {
			setNextPageNo(finalPage);
		}else {
			setNextPageNo((nowPageNo+1)>finalPage? finalPage:(nowPageNo+1));			
		}
		
		setLastPageNo(finalPage);
	}
}
