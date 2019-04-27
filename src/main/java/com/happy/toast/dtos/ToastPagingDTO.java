package com.happy.toast.dtos;

public class ToastPagingDTO {
	
	private int pageListSize; 		// ?˜?´ì§? ëª©ë¡?˜ ?‚¬?´ì¦? (ex) 10ê°œë¡œ ? •?•˜ë©? <1~10> 5ê°œë¡œ ? •?•˜ë©? <1~5>) 	
	private int nowPageNo;    		// ?˜„?¬ ?˜?´ì§? ë²ˆí˜¸
	private int totalBoardSize;     // ì´? ê²Œì‹œê¸? ê°œìˆ˜
	private int pageBoardSize;      // ?˜?´ì§??‹¹ ë³´ì—¬ì¤? ê²Œì‹œê¸? ?¬ê¸? (ex)5ë¡? ? •?•˜ë©? 1?˜?´ì§??‹¹ ê²Œì‹œê¸? 5ê°œë?? ë³´ì—¬ì¤?)
	
	private int firstPageNo; 		// ì²˜ìŒ ?˜?´ì§? ë²ˆí˜¸  (ë§Œì•½ ?˜?´ì§? ê°œìˆ˜ê°? ì´? 42ê°œë¼ë©? firstPageNo?? 1)
	private int lastPageNo; 		// ë§ˆì?ë§? ?˜?´ì§? ë²ˆí˜¸  (ë§Œì•½ ?˜?´ì§? ê°œìˆ˜ê°? ì´? 42ê°œë¼ë©? lastPageNo?? 1)
	
	private int prevPageNo; 		// ?˜„?¬ ?˜?´ì§??—?„œ ?´? „ ?˜?´ì§? ë²ˆí˜¸
	private int nextPageNo; 		// ?˜„?¬ ?˜?´ì§??—?„œ ?‹¤?Œ ?˜?´ì§? ë²ˆí˜¸
	
	private int startPageNo; 		// ?˜„?¬ ?˜?´ì§? ëª©ë¡?“¤(ex)<11~20>)?—?„œ ?‹œ?‘ ?˜?´ì§? ë²ˆí˜¸ 
	private int endPageNo;   		// ?˜„?¬ ?˜?´ì§? ëª©ë¡?“¤(ex)<11~20>)?—?„œ ë§¨ë ?˜?´ì§? ë²ˆí˜¸
	
	private int firstBoardNo;		// ?˜„?¬ ?˜?´ì§??—?„œ ?‹œ?‘ ê²Œì‹œê¸? ë²ˆí˜¸
	private int endBoardNo;			// ?˜„?¬ ?˜?´ì§??—?„œ ë§ˆì?ë§? ê²Œì‹œê¸? ë²ˆí˜¸
	
	
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
		
		int finalPage = (totalBoardSize+(pageBoardSize-1))/pageBoardSize; // ë§ˆì?ë§? ?˜?´ì§? ë²ˆí˜¸
		
		// ?˜„?¬ ?˜?´ì§?ê°? ë§ˆì?ë§? ?˜?´ì§?ë³´ë‹¤ ?´ê²½ìš° ?˜„?¬ ?˜?´ì§?ë¥? ë§ˆì?ë§? ?˜?´ì§?ë¡? ?„¤? •
		if(nowPageNo > finalPage) {
			setNowPageNo(finalPage);			
		}
		
		boolean isPageNowFirst = (nowPageNo == 1)? true:false; 			// ?˜„?¬ ?˜?´ì§?ê°? ë§¨ì²˜?Œ?¸ì§?
		boolean isPageNowLast = (nowPageNo == finalPage)? true:false;	// ?˜„?¬ ?˜?´ì§?ê°? ë§ˆì?ë§‰ì¸ì§?
		
		// ?˜„?¬ ?˜?´ì§? ë²ˆí˜¸?—?„œ ë³´ì—¬ì¤? ?˜?´ì§? ë¦¬ìŠ¤?Š¸ ë²ˆí˜¸ (ex) ?˜„?¬ ?˜?´ì§?ê°? 8?´?¼ë©? <6~10>)
		int startPage = ((nowPageNo-1)/pageListSize)*pageListSize+1;
		int endPage = startPage+(pageListSize-1);
		
		// ?˜„?¬ ?˜?´ì§? ë²ˆí˜¸?—?„œ ë³´ì—¬ì¤? ê²Œì‹œê¸? ë²ˆí˜¸?“¤ (ex) ?˜„?¬ ?˜?´ì§?ê°? 8?´?¼ë©? 71~80ê°œì˜ ê²Œì‹œê¸?)
		int startBoard = nowPageNo*pageBoardSize-(pageBoardSize-1);
		int endBoard = nowPageNo*pageBoardSize;
		
		// endPageê°? ë§ˆì?ë§? ?˜?´ì§?ë¥? ?„˜?„?ˆ˜ ?—†?„ë¡?
		if(endPage > finalPage) {
			endPage = finalPage;
		}
		
		// ?˜„?¬ ?˜?´ì§?ê°? ì²«ë²ˆì§¸í˜?´ì§??¼ë©? 
		if(isPageNowFirst) {
			setPrevPageNo(1);
		}else {
			setPrevPageNo((nowPageNo-1)<0? 1:(nowPageNo-1));
		}
		// ?˜?´ì§? ?Š¤???Š¸,?—”?“œ ë²ˆí˜¸
		setStartPageNo(startPage);
		setEndPageNo(endPage);
		// ê²Œì‹œê¸? ?Š¤???Š¸,?—”?“œ ë²ˆí˜¸ 
		setFirstBoardNo(startBoard);
		setEndBoardNo(endBoard);
		// ?˜„?¬ ?˜?´ì§?ê°? ë§ˆì?ë§‰í˜?´ì§??¼ë©?
		if(isPageNowLast) {
			setNextPageNo(finalPage);
		}else {
			setNextPageNo((nowPageNo+1)>finalPage? finalPage:(nowPageNo+1));			
		}
		
		setLastPageNo(finalPage);
	}
}
