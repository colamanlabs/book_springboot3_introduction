package org.example.firstproject.dto;

import org.example.firstproject.entity.Article;

public class ArticleForm
{
    private final String title;
    private final String content;

    public ArticleForm(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "ArticleForm{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
    }

    public Article toEntity()
    {
        return new Article(null, title, content);
    }
}
