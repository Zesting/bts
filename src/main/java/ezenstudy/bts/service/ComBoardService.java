package ezenstudy.bts.service;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.repository.ComBoardRepository;

public class ComBoardService {

    private final ComBoardRepository comBoardRepository;


    public ComBoardService(ComBoardRepository comBoardRepository) {
        this.comBoardRepository = comBoardRepository;
    }

    public ComBoard getComBoardById(Long id) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardRepository.findById(id));
        if (comBoard.isPresent()) {
            return comBoard.get();
        } else {
            throw new NoSuchElementException( id + "no search.");
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

    public void addFile(ComBoard comBoard, MultipartFile file) throws Exception{

        UUID uuid = UUID.randomUUID();

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        comBoardRepository.save(comBoard);
    }
}
