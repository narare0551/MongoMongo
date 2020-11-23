package org.knr.controller;

import org.knr.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardConroller {
	@Autowired
	MongoTemplate mongoTemplate;
	
	 //http://localhost:8000/board/register
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	
	@GetMapping("/read")
	public String read(){
		return "read";
		
		
	}

	@GetMapping("/register")
	public String saveForm() {
		return "register";
	}
	
	@PostMapping("/save")
	//json을 받으려면 얘가 있어야 한다. 
	@ResponseBody
	public String register(@RequestBody Board board) {
		board.setBno(1l);
		board.setWriter("knr");
		//Board board = new Board(3L, "title3", "content3", "user03");
		mongoTemplate.insert(board);
		//log.info("board insert"+board);
		
		
		
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		System.out.println("controller here");
		return "ok";
	}
//	
//	@PostMapping("/user/matchApply/{teamid}") 
//	public ResponseEntity<?> matchApply(@RequestBody Battle battle,@PathVariable int teamid){
//		User user = (User) session.getAttribute("principal");
//		return battleService.matchApply(battle,teamid,user);
//	}
	

}





// contoller의 method의 파라메터 부분은 자동 DI가 됨