package kr.ac.jejunu.userdao;

import lombok.*;

//@Getter
//@Setter
@Data
@Builder
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 지금 존재하는 property에 대한 모든 생성자
public class User {
    private Integer id;
    private String name;
    private String password;
}