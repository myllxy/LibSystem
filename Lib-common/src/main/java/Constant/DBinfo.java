package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-08-21 12:17
 */
public enum DBinfo implements Val {
    USER("root"), PWD("276689237abc"), URL("jdbc:mysql://127.0.0.1:3306/Librarymanagementsystem?useSSL=true"),;
    private String name;

    DBinfo(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
