package kr.ac.jejunu;

public class User {
    // 왜 int가 아니고 Integer? \
    // int(primitive)는 null을 허용하지 않는다. 또한 default 값이 0이다. 따라서 Object 타입을 쓴다.

    private Integer id;
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
