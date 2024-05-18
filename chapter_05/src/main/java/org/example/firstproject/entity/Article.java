package org.example.firstproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;



@AllArgsConstructor
// 디폴트 생성자
@NoArgsConstructor

@ToString
@Entity     // 엔티티선언
public class Article
{
    @Id     // 엔티티의 대푯값 지정
    @GeneratedValue     // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;

    @Column
    private String title;       // title 필드 선언. DB 테이블의 title 열과 연결됨

    @Column
    private String content;     // content 필드 선언. DB 테이블의 content 열과 연결됨
}
