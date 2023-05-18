package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Payment {
    private String card;
    private String cardNum;
    private Integer amount;
}
