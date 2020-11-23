package org.knr;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.knr.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import lombok.extern.java.Log;
//insert test 
@Log
@SpringBootTest
public class TestTemplate {
	//autowired로 자동 주입 해주고 
	//mongodb에 데이터 입출력 할 수 있는 메소드들이 있음 
	//이걸로mongodb에 넣어줌 
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Test
	public void insertTest() {
		//객체 하나 생성 
		Board board = new Board(3L, "title3", "content3", "user03");
		//데이터가 들어감 
		mongoTemplate.insert(board);
		log.info("board insert"+board);
		
	}
	
	
	//Select all test 
	@Test
	public void selectAllTest() {
		//모든 board를 찾아서list에 저장시켜 줍니다. 
		List<Board> list =mongoTemplate.findAll(Board.class,"board");
		//각각의 board에 대한 info를 log에 찍어줍니다. 
	
		list.forEach(board->log.info("board:"+board));
	}
	//특정 조건을 가진 
	@Test
	public void findCriteria() {
		//타이틀 필드를 조건으로 쓰겠습니다. 
		Criteria criteria = new Criteria("title"); 
		//title이 title1인것 
		criteria.is("title1");
		//query만들기 
		Query query = new Query(criteria); 
		//entity하고 collection하고 같이 묶여있어서 생략한다
		List<Board> list = mongoTemplate.find(query, Board.class,"board"); 
		list.forEach(board->log.info("board:"+board));
		
	}
	@Test
	public void updateTest() {
		//bno를 조건으로 쓰겠습니다. 
		Criteria criteria = new Criteria("bno"); 
	 //bno가 1L인 것이 기준입니다. 
		criteria.is(1l);
		//query 작성 
		Query query = new Query(criteria); 
		Update update = new Update(); 
		
		//업데이트 해 줄 내용 
		update.set("content", "content_changed 1");
		update.set("title", "changed_title 1 ");
		//mongoTemplate.updateFirst(query, update, entityClass)
		mongoTemplate.updateFirst(query, update, Board.class); 
		//mongoTemplate.updateMulti(query, update, Board.class); 
		
		
	}
	
	@Test
	public void deleteTest() {
		Criteria criteria = new Criteria("bno"); 
		 //bno가 1L인 것이 기준입니다. 
		criteria.is(1l);
		Query query = new Query(criteria); 
		mongoTemplate.remove(query, Board.class); 
	}
	
	@Test
	public void countTest() {
		long count = mongoTemplate.count(new Query(), Board.class);
		log.info("count"+count);
		
	}
}
