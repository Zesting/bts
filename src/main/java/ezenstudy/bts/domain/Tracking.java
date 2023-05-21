package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Tracking {
    private String t_code; // 택배사 코드
    private String t_invoice; // 운송장 번호
    private String t_key; // 발급받은 키

    public String getT_code() {
        return this.t_code;
    }

    public void setT_code(String t_code) {
        this.t_code = t_code;
    }

    public String getT_invoice() {
        return this.t_invoice;
    }

    public void setT_invoice(String t_invoice) {
        this.t_invoice = t_invoice;
    }

    public String getT_key() {
        return this.t_key;
    }

    public void setT_key(String t_key) {
        this.t_key = t_key;
    }

}
