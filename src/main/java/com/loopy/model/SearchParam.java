package com.loopy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//모든 매개변수를 가지는 생성자를 추가해줌.
@AllArgsConstructor
public class SearchParam {

    private String account;
    private String email;
    private int page;





    /*
    //Code->generates->getter and setter 로 자동으로 생성합니다.
    public String getAccount() {
        return account;
    }
    public String getEmail() { return email; }
    public int getPage() { return page; }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPage(int page) {
        this.page = page;
    }

     */
}
