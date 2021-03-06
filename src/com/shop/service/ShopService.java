package com.shop.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.shop.model.dao.ShopDao;
import com.shop.model.vo.Minimi;
import com.shop.model.vo.MinimiLike;
import com.shop.model.vo.Music;
import com.shop.model.vo.MusicLike;
import com.shop.model.vo.Skin;
import com.shop.model.vo.SkinLike;


public class ShopService {
	
private ShopDao dao= new ShopDao();
	
	public List<Music> musicList(){
		Connection conn=getConnection();
		List<Music> list =dao.musicList(conn); 
		close(conn);
		return list;
	}
	public List<Minimi> minimiList(){
		Connection conn=getConnection();
		List<Minimi> list =dao.minimiList(conn); 
		close(conn);
		return list;
	}
	public List<Skin> skinList(){
		Connection conn=getConnection();
		List<Skin> list =dao.skinList(conn); 
		close(conn);
		return list;
	}
	public List<String> searchTitle(String type,String keyword) {
		Connection conn=getConnection();
		List<String> list=dao.searchTitle(conn,type,keyword);
		close(conn);
		return list;
	}
	public int insertShoppingList(String id,List<String> itemNums,String type){
		Connection conn=getConnection();
		int result=dao.insertShoppingList(conn,id,itemNums,type);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}
	public List<Minimi> shoppingList1(String memberId){
		Connection conn=getConnection();
		List<Minimi> a=dao.shoppingList1(conn,memberId);
		close(conn);
		return a;
	}
	public List<Skin> shoppingList2(String memberId){
		Connection conn=getConnection();
		List<Skin> b=dao.shoppingList2(conn,memberId);
		close(conn);
		return b;
	}
	public List<Music> shoppingList3(String memberId){
		Connection conn=getConnection();
		List<Music> c=dao.shoppingList3(conn,memberId);
		close(conn);
		return c;
	}
	public List<Minimi> ashopSearchItem(String searchKey){
		Connection conn=getConnection();
		List<Minimi> minimiSearchResult=dao.ashopSearchItem(conn,searchKey);
		close(conn);
		return minimiSearchResult;
	}
	public List<Skin> bshopSearchItem(String searchKey){
		Connection conn=getConnection();
		List<Skin> skinSearchResult=dao.bshopSearchItem(conn,searchKey);
		close(conn);
		return skinSearchResult;
	}
	public List<Music> cshopSearchItem(String searchKey){
		Connection conn=getConnection();
		List<Music> musicSearchResult=dao.cshopSearchItem(conn,searchKey);
		close(conn);
		return musicSearchResult;
	}
	public List<Minimi> ashopTotalOrder(){
		Connection conn=getConnection();
		List<Minimi> minimiTotalTitleOrder=dao.ashopTotalOrder(conn);
		close(conn);
		return minimiTotalTitleOrder;
	}
	public List<Skin> bshopTotalOrder(){
		Connection conn=getConnection();
		List<Skin> skinTotalTitleOrder=dao.bshopTotalOrder(conn);
		close(conn);
		return skinTotalTitleOrder;
	}
	public List<Music> cshopTotalOrder(){
		Connection conn=getConnection();
		List<Music> musicTotalTitleOrder=dao.cshopTotalOrder(conn);
		close(conn);
		return musicTotalTitleOrder;
	}
	public List<Minimi> ashopLikeOrder(){
		Connection conn=getConnection();
		List<Minimi> minimiLikeOrder=dao.ashopLikeOrder(conn);
		close(conn);
		return minimiLikeOrder;
	}
	public List<Skin> bshopLikeOrder(){
		Connection conn=getConnection();
		List<Skin> skinLikeOrder=dao.bshopLikeOrder(conn);
		close(conn);
		return skinLikeOrder;
	}
	public List<Music> cshopLikeOrder(){
		Connection conn=getConnection();
		List<Music> musicLikeOrder=dao.cshopLikeOrder(conn);
		close(conn);
		return musicLikeOrder;
	}
	public List<Minimi> ashopPriceOrder(){
		Connection conn=getConnection();
		List<Minimi> minimiPriceOrder=dao.ashopPriceOrder(conn);
		close(conn);
		return minimiPriceOrder;
	}
	public List<Skin> bshopPriceOrder(){
		Connection conn=getConnection();
		List<Skin> skinPriceOrder=dao.bshopPriceOrder(conn);
		close(conn);
		return skinPriceOrder;
	}
	public List<Music> cshopPriceOrder(){
		Connection conn=getConnection();
		List<Music> musicPriceOrder=dao.cshopPriceOrder(conn);
		close(conn);
		return musicPriceOrder;
	}
	public List<Minimi> ashopPopularOrder(){
		Connection conn=getConnection();
		List<Minimi> minimiPopularOrder=dao.ashopPopularOrder(conn);
		close(conn);
		return minimiPopularOrder;
	}
	public List<Skin> bshopPopularOrder(){
		Connection conn=getConnection();
		List<Skin> skinPopularOrder=dao.bshopPopularOrder(conn);
		close(conn);
		return skinPopularOrder;
	}
	public List<Music> cshopPopularOrder(){
		Connection conn=getConnection();
		List<Music> musicPopularOrder=dao.cshopPopularOrder(conn);
		close(conn);
		return musicPopularOrder;
	}
	public int aIsInThereShoppingList(String id,List<String> aitemNo) {
		Connection conn=getConnection();
		int aSearchResult=dao.aIsInThereShoppingList(conn,id,aitemNo);
		close(conn);
		return aSearchResult;
	}
	public int bIsInThereShoppingList(String id,List<String> bitemNo) {
		Connection conn=getConnection();
		int bSearchResult=dao.bIsInThereShoppingList(conn,id,bitemNo);
		close(conn);
		return bSearchResult;
	}
	public int cIsInThereShoppingList(String id,List<String> citemNo) {
		Connection conn=getConnection();
		int cSearchResult=dao.cIsInThereShoppingList(conn,id,citemNo);
		close(conn);
		return cSearchResult;
	}
	public int aGetItemShoppingList(String id,List<String> aitemNo) {
		Connection conn=getConnection();
		int aResultInput=dao.aGetItemShoppingList(conn,id,aitemNo);
		if(aResultInput>0) commit(conn);
		else rollback(conn);
		close(conn);
		return aResultInput;
	}
	public int bGetItemShoppingList(String id,List<String> bitemNo) {
		Connection conn=getConnection();
		int bResultIntput=dao.bGetItemShoppingList(conn,id,bitemNo);
		if(bResultIntput>0) commit(conn);
		else rollback(conn);
		close(conn);
		return bResultIntput;
	}
	public int cGetItemShoppingList(String id,List<String> citemNo) {
		Connection conn=getConnection();
		int cResultInput=dao.cGetItemShoppingList(conn,id,citemNo);
		if(cResultInput>0) commit(conn);
		else rollback(conn);
		close(conn);
		return cResultInput;
	}
	public int aBuyDeleteShoppingList(String id,List<String> aitemNo) {
		Connection conn=getConnection();
		int aResult=dao.aBuyDeleteShoppingList(conn,id,aitemNo);
		if(aResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		return aResult;
	}
	public int bBuyDeleteShoppingList(String id,List<String> bitemNo) {
		Connection conn=getConnection();
		int bResult=dao.bBuyDeleteShoppingList(conn,id,bitemNo);
		if(bResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		return bResult;
	}
	public int cBuyDeleteShoppingList(String id,List<String> citemNo) {
		Connection conn=getConnection();
		int cResult=dao.cBuyDeleteShoppingList(conn,id,citemNo);
		if(cResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		return cResult;
	}
	public int dotoriMinusShoppingList(String id,int myDotoriNums,int itemDotoriNums) {
		Connection conn=getConnection();
		int dotoriResult=dao.dotoriMinusShoppingList(conn,id,myDotoriNums,itemDotoriNums);
		if(dotoriResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		return dotoriResult;
	}
	public int aDeleteInShoppingList(String id,List<String> aitemNo) {
		Connection conn=getConnection();
		int aResultDelete=dao.aDeleteInShoppingList(conn,id,aitemNo);
		if(aResultDelete>0) commit(conn);
		else rollback(conn);
		close(conn);
		return aResultDelete;
	}
	public int bDeleteInShoppingList(String id,List<String> bitemNo) {
		Connection conn=getConnection();
		int bResultDelete=dao.bDeleteInShoppingList(conn,id,bitemNo);
		if(bResultDelete>0) commit(conn);
		else rollback(conn);
		close(conn);
		return bResultDelete;
	}
	public int cDeleteInShoppingList(String id,List<String> citemNo) {
		Connection conn=getConnection();
		int cResultDelete=dao.cDeleteInShoppingList(conn,id,citemNo);
		if(cResultDelete>0) commit(conn);
		else rollback(conn);
		close(conn);
		return cResultDelete;
	}
	public List<MinimiLike> aSearchHeartList(){
		Connection conn=getConnection();
		List<MinimiLike> aheartList=dao.aSearchHeartList(conn);
		close(conn);
		return aheartList;
	}
	public List<SkinLike> bSearchHeartList(){
		Connection conn=getConnection();
		List<SkinLike> bheartList=dao.bSearchHeartList(conn);
		close(conn);
		return bheartList;
	}
	public List<MusicLike> cSearchHeartList(){
		Connection conn=getConnection();
		List<MusicLike> cheartList=dao.cSearchHeartList(conn);
		close(conn);
		return cheartList;
	}
	public List<Integer> aMyHeartItemNoList(String memberId){
		Connection conn=getConnection();
		List<Integer> myHeartItemList=dao.aMyHeartItemNoList(conn,memberId);
		close(conn);
		return myHeartItemList;
	}
	public List<Integer> bMyHeartItemNoList(String memberId){
		Connection conn=getConnection();
		List<Integer> myHeartItemList=dao.bMyHeartItemNoList(conn,memberId);
		close(conn);
		return myHeartItemList;
	}
	
	public List<Integer> cMyHeartItemNoList(String memberId){
		Connection conn=getConnection();
		List<Integer> myHeartItemList=dao.cMyHeartItemNoList(conn,memberId);
		close(conn);
		return myHeartItemList;
	}
	public int aHeartCount(int itemNo) {
		Connection conn=getConnection();
		int aHeartResult=dao.aHeartCount(conn,itemNo);
		close(conn);
		return aHeartResult;
	}
	public int bHeartCount(int itemNo) {
		Connection conn=getConnection();
		int bHeartResult=dao.bHeartCount(conn,itemNo);
		close(conn);
		return bHeartResult;
	}
	public int cHeartCount(int itemNo) {
		Connection conn=getConnection();
		int cHeartResult=dao.cHeartCount(conn,itemNo);
		close(conn);
		return cHeartResult;
	}
	public void aHeartInsert(String id,int itemNo) {
		Connection conn=getConnection();
		int aHeartResult=dao.aHeartInsertResult(conn,id,itemNo);
		if(aHeartResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	public void bHeartInsert(String id,int itemNo) {
		Connection conn=getConnection();
		int bHeartResult=dao.bHeartInsertResult(conn,id,itemNo);
		if(bHeartResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	public void cHeartInsert(String id,int itemNo) {
		Connection conn=getConnection();
		int cHeartResult=dao.cHeartInsertResult(conn,id,itemNo);
		if(cHeartResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
