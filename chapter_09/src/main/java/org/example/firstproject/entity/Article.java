package org.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@AllArgsConstructor
// 디폴트 생성자
@NoArgsConstructor

@Getter
@ToString
@Entity     // 엔티티선언
public class Article
{
    @Id     // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 가 id 자동 생성
    private Long id;

    @Column
    private String title;       // title 필드 선언. DB 테이블의 title 열과 연결됨

    @Column
    private String content;     // content 필드 선언. DB 테이블의 content 열과 연결됨
}
