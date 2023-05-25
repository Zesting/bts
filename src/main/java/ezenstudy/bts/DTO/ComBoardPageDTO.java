package ezenstudy.bts.DTO;

import ezenstudy.bts.domain.ComBoard;
import lombok.Data;

@Data
public class ComBoardPageDTO {
    private int startPage; //시작 페이지

    private int endPage; //끝 페이지

    private boolean prev, next;

    private int total;

    private ComBoard com;

}
