package ezenstudy.bts.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.repository.ComBoardRepository;
import jakarta.servlet.ServletContext;

public class ComBoardService {

    private final ComBoardRepository comBoardRepository;


    public ComBoardService(ComBoardRepository comBoardRepository) {
        this.comBoardRepository = comBoardRepository;
    }

    public ComBoard getComBoardById(Long id) {
        ComBoard comBoard = comBoardRepository.findById(id);
    if (comBoard != null) {
        return comBoard;
    } else {
        throw new NoSuchElementException(id + "no search.");
    }
    }


    public List<ComBoard> getAllComBoards() {
        return comBoardRepository.findAll();
    }

    public ComBoard save(ComBoard comBoard) {
        return comBoardRepository.save(comBoard);
    }

    public ComBoard saveComBoard(ComBoard comBoard) {
        return comBoardRepository.save(comBoard);
    }
    public ComBoard update(Long id, ComBoard updatedComBoard) {
        ComBoard comBoard = getComBoardById(id);
        comBoard.setTitle(updatedComBoard.getTitle());
        comBoard.setContent(updatedComBoard.getContent());
        comBoard.setBN(updatedComBoard.getBN());
        comBoard.setAnswer(updatedComBoard.getAnswer());
        return comBoardRepository.save(comBoard);
    }
    //삭제
    public void delete(Long id) {
        comBoardRepository.delete(id);
    }
    // BN입력창
    public ComBoard getComBoard(Long id, String BN) {
        ComBoard comBoard = comBoardRepository.findById(id).orElse(null);
        if (comBoard != null && comBoard.getBN().equals(BN)) {
            return comBoard;
        }
        return null;
    }

    public ComBoard findById(Long id) {
        return comBoardRepository.findById(id);
    }

    @Autowired
private ServletContext servletContext;

public void addFile(ComBoard comBoard, MultipartFile file) throws Exception {
    String projectPath = servletContext.getRealPath("/files/");

        File uploadDirectory = new File(projectPath);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        String fullPath = projectPath + fileName;
        File saveFile = new File(fullPath);

        try {
            file.transferTo(saveFile);
            comBoard.setFileName(fileName);
            comBoard.setFilePath(fullPath);
            comBoardRepository.save(comBoard);
        } catch (IOException e) {
            System.out.println("파일 저장 실패");
            e.printStackTrace();
            throw new Exception("파일을 불러오지 못했습니다." + e.getMessage());
        }
    }
}
