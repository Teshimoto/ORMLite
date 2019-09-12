package com.example.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Article.TABLE_NAME)
public class Article {
    public static final String TABLE_NAME = "FakeData";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_ARTICLENAME = "articleName";
    public static final String COLUMN_NAME_TEXT = "articleText";

    @DatabaseField(generatedId = true, columnName = Article.COLUMN_NAME_ID)
    private long id;
    @DatabaseField(columnName = Article.COLUMN_NAME_ARTICLENAME)
    private String articleName;
    @DatabaseField(columnName = Article.COLUMN_NAME_TEXT)
    private String articleText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }
}
