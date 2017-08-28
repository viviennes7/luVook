package com.ms.luvook.board.controller;

import java.util.List;

import com.ms.luvook.board.domain.BoardComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ms.luvook.board.domain.Board;
import com.ms.luvook.board.domain.BookBoard;
import com.ms.luvook.board.domain.MovieBoard;
import com.ms.luvook.board.service.BoardService;
import com.ms.luvook.common.domain.Result;

/**
 * Created by vivie on 2017-07-17.
 */
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @PostMapping("/board/book")
    public Result saveBook(BookBoard bookBoard){
        Result result = Result.successInstance(); 
        int boardId = boardService.save(bookBoard);
    	return result;
    }
    
    @PostMapping("/board/movie")
    public Result saveMovie(MovieBoard movieBoard){
        Result result = Result.successInstance();
        int boardId = boardService.save(movieBoard);
    	return result;
    }

    @GetMapping("/board/{boardId}")
    public Result find(@PathVariable int boardId){
    	Result result = Result.successInstance();
    	Board board = boardService.find(boardId);
    	result.setData(board);
        return result;
    }

    @DeleteMapping("/board/{boardId}")
    public Result delete(@PathVariable int boardId){
    	Result result = Result.successInstance();
    	boardService.delete(boardId);
    	return result;
    }

    //임시로 Post - > Patch
    @PostMapping(value = "/board/book/temp")
    public Result update(BookBoard bookBoard){
    	Result result = Result.successInstance();
    	boardService.update(bookBoard);
    	return result;
    }

    @GetMapping("/boards/{pageNum}")
    public Result findAll(@PathVariable int pageNum){
    	Result result = Result.successInstance();
    	List<Board> boards = boardService.findAll(pageNum);
    	result.setData(boards);
        return result;
    }

    @GetMapping("/member/{memberId}/boards")
    public Result findAllByMember(@PathVariable int memberId){
    	Result result = Result.successInstance();
    	List<Board> boards = boardService.findAllByMember(memberId);
    	result.setData(boards);
    	return result;
    }
    
    @PostMapping("/board/{boardId}/heart")
    public Result toggleHeart(int memberId, @PathVariable int boardId){
    	Result result = Result.successInstance();
    	boardService.toggleHeart(memberId, boardId);
    	
    	return result;
    }
    
    @PostMapping("/board/{boardId}/comment")
    public Result saveComment(@PathVariable  int boardId, BoardComment comment){
        Result result = Result.successInstance();
        BoardComment savedComment = boardService.saveComment(comment);
        result.setData(savedComment);
        return result;
    }

    @GetMapping("/board/{boardId}/comment")
    public Result findAllComment(@PathVariable int boardId){
        Result result = Result.successInstance();
        List<BoardComment> comments = boardService.findAllComment(boardId);
        result.setData(comments);
        return result;
    }

    @PutMapping("/board/{boardId}/comment/{commentId}")
    public Result updateComment(@PathVariable int boardId, @PathVariable int commentId, BoardComment comment){
        Result result = Result.successInstance();
        boardService.updateComment(comment);
        return result;
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public Result deleteComment(@PathVariable int boardId, @PathVariable int commentId){
        Result result = Result.successInstance();
        boardService.deleteComment(commentId);
        return result;
    }
}
