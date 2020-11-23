package org.knr.repository;

import org.knr.domain.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
//MongoRepository L 저장소 
//           <Board : 도메인 클래스 , id로 사용할 필드의 데이터형 >
public interface BoardRepository extends MongoRepository<Board, Long>{

}
