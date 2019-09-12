package com.example.ormlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    RecyclerViewAdapter adapter;
    private DatabaseHelper dbHelper = DatabaseHelper.getInstance();
    private FakeData fakeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.view_user_list);

        fakeData = new FakeData();
        adapter = new RecyclerViewAdapter(this, fakeData);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        new SelectTask().execute();
    }

    public void onInsertButtonClick(View view) {
        new InsertTask().execute();
    }

    public void onDeleteButtonClick(View view) {
        new DeleteTask().execute();
    }

    private class SelectTask extends AsyncTask<Void, Void, List<Article>> {

        @Override
        protected List<Article> doInBackground(Void... voids) {
            try {
                Dao<Article, Long> dao = dbHelper.getDao(Article.class);

                return dao.queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            adapter.setData(articles);
        }
    }

    private class InsertTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(final Void... params) {

            String[] articleNames = fakeData.getNames();
            String[] articleText = fakeData.getText();
            try {
                Dao<Article, Long> dao = dbHelper.getDao(Article.class);
                for (int i = 0; i < articleNames.length; i++) {
                    Article article = new Article();
                    article.setArticleName(articleNames[i]);
                    article.setArticleText(articleText[i]);
                    dao.create(article);
                    Log.d("Insert", "doInBackground: " + article.getArticleName());
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(final Void aVoid) {
            new SelectTask().execute();
        }
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(final Void... params) {
            try {
                Dao<Article, Long> dao = dbHelper.getDao(Article.class);
                dao.deleteBuilder().delete();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Void aVoid) {
            new SelectTask().execute();
        }
    }
}
