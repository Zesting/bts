package ezenstudy.bts.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.service.ComBoardService;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ComBoardController {

    private final ComBoardService comBoardService;

    public ComBoardController(ComBoardService comBoardService) {
        this.comBoardService = comBoardService;
    }

    @GetMapping("/comboard/list")
    public String listComBoards(Model model) {
        List<ComBoard> comBoardList = comBoardService.getAllComBoards();
        model.addAttribute("comBoardList", comBoardList);
        return "comboard/comlist";
    }

    @PostMapping("/comboard/list")
    public String listComBoardsPost(Model model) {
        List<ComBoard> comBoardList = comBoardService.getAllComBoards();
        model.addAttribute("comBoardList", comBoardList);
        return "comboard/comlist";
    }

    @GetMapping("/comboard/create")
    public String showCreateForm() {
        return "comboard/ComBoardwrite";
    }

    /* upload */
    @PostMapping("/comboard/create")
    public String addFile(@RequestParam String filename,
                            @RequestParam MultipartFile file) throws IOException {
        System.out.println("filename = " + filename);

        if(!file.isEmpty()){
            String fullPath = "/C:/image/" + file.getOriginalFilename();
            System.out.println("파일 저장 fullPath = " + fullPath);
            file.transferTo(new File(fullPath));
        }

        return "comboard/comshow";
    }
    
    @GetMapping("/comboard/answer/{id}")
    public String answer(Model model, @PathVariable("id") Long id){
        ComBoard comBoard = comBoardService.findById(id);
        model.addAttribute("comBoard", comBoard);
        return "comboard/comboardanswer";
    }

    @PostMapping("/comboard/answer")
    public String answerpost(@RequestParam("comboardId") Long id, @RequestParam("answer") String answer, Model model) {

        ComBoard comBoard = comBoardService.findById(id);
        model.addAttribute("comBoard", comBoard);
        
         return "comboard/comshow";
        //return "redirect:/";
    }

    // @PostMapping("/comboard/answer")
    // public String answerpost(@RequestParam("comboardId") String comboardId, @RequestParam("answer") String answer, Model model) {

    //     System.out.println("comboardId: " + comboardId);
    //     System.out.println("answer: " + answer);

    //     Long id = Long.parseLong(comboardId);
    //     ComBoard comBoard = comBoardService.findById(id);
    //     model.addAttribute("comBoard", comBoard);
        
    //      return "comboard/comshow";
    //     //return "redirect:/";
    // }


    // @PostMapping("/comboard/answer")
    // public String answerpost(Model model){
    //     System.out.println("comboardId: "+(String)model.getAttribute("getComboardId()"));
    //     System.out.println("answer: "+(String)model.getAttribute("getAnswer()"));

    //     return "redirect:/";
    // }
    // 답변 처리
    // @GetMapping("/comboard/show/reply/{id}")
    // public String showReplyForm(@PathVariable("id") Long id, Model model) {
    //     ComBoard comBoard = comBoardService.findById(id);
    //     if (comBoard == null){
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard not found");
    //     }
    //     model.addAttribute("comBoard", comBoard);
    //     String bn = comBoard.getBN();
    //     model.addAttribute("bn", bn);
    //     return "comboard/comReply";
    // }

    // @PostMapping("/comboard/reply/{id}")
    // public String submitReply(@PathVariable("id") Long id, @ModelAttribute("comBoard") ComBoard comBoard) {
        
    //     return "redirect:/comboard/list";
    // }

    /** show */
    @PostMapping("/show")
    public String createComBoard(ComBoard comBoard) {
        ComBoard savedComBoard = comBoardService.save(comBoard);
        Long id = savedComBoard.getId();
        return "redirect:/comboard/show/" + id;
    }

    @GetMapping("/comboard/show/{id}")
    public String showComBoard(@PathVariable("id") Long id, Model model) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardService.getComBoardById(id));
        if (comBoard.isPresent()) {
            model.addAttribute("comBoard", comBoard.get());
            return "comboard/comshow";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard no");
        }
    }

    /** 수정 */
    @GetMapping("/comboard/modify/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardService.getComBoardById(id));
        if (comBoard.isPresent()) {
            model.addAttribute("comBoard", comBoard.get());
            return "comboard/comupdate";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard no");
        }
    }

    @PostMapping("/comboard/modify/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("comBoard") ComBoard comBoard) {
        comBoardService.update(id, comBoard);
        return "redirect:/comboard/show/" + id;
    }

    /** 삭제 */

    @PostMapping(value = "/comboard/delete/{boardId}", params = "_method=DELETE")
    public String deletePost(@PathVariable("boardId") Long boardId) {
        comBoardService.delete(boardId);
        return "redirect:/comboard/list";
    }

    /** 비번 */

    @PostMapping("/comboard/check-bn/{id}")
    public String checkBN(@PathVariable("id") Long id, @ModelAttribute("comBoard") ComBoard comBoard, @RequestParam("bn") String bn) {
        ComBoard ComBoard = comBoardService.getComBoardById(id);
        ComBoard.setBN(bn);
        if (ComBoard.getBN().equals(bn)) {
            System.out.println("일치합니다.");
            return "redirect:/comboard/show/" + id;
        } else {
            System.out.println("일치하지않습니다.");
            return "redirect:/comboard/list";
        }
    }


    @GetMapping("/comboard/check-bn/{id}")
    public String checkBN(@PathVariable("id") Long id, Model model) {
        ComBoard comBoard = comBoardService.getComBoardById(id);
        model.addAttribute("comBoard", comBoard);
        return "comboard/comcheck";
    }

}
