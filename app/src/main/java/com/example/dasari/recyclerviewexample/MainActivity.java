package com.example.dasari.recyclerviewexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.dasari.snakerecycler.DataModel;
import com.example.dasari.snakerecycler.RecyclerViewAdapter;
import com.example.dasari.snakerecycler.SnakeGridLayoutManager;
import com.example.dasari.snakerecycler.SnakeItemTouchHelper;
import com.example.dasari.snakerecycler.SnakeRecyclerJson;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Dasari on 25-10-2017.
 */

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {

    private RecyclerView mRecyclerView;
    private ArrayList<DataModel> mDataModels;
    private RecyclerViewAdapter mAdapter;
    private SnakeGridLayoutManager.GridSpanLookup mGridSpanLookup;
    private int count;
    private SnakeGridLayoutManager mSnakeGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDataModels = new ArrayList<>();
        mDataModels.add(new DataModel("0", "UUID", "", DataModel.TileType.PLUS));

        mAdapter = new RecyclerViewAdapter(this, mDataModels, this);
        mRecyclerView.setAdapter(mAdapter);

        mGridSpanLookup = new SnakeGridLayoutManager.GridSpanLookup() {
            @Override
            public SnakeGridLayoutManager.SpanInfo getSpanInfo(int position) {
                if (position == 0) {
                    return new SnakeGridLayoutManager.SpanInfo(2, 2);
                } else {
                    return new SnakeGridLayoutManager.SpanInfo(1, 1);
                }
            }
        };

        mSnakeGridLayoutManager = new SnakeGridLayoutManager(mGridSpanLookup, 3, 1f);
        mRecyclerView.setLayoutManager(mSnakeGridLayoutManager);

        ItemTouchHelper.Callback callback = new SnakeItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        new DownloadJson().execute();
    }

    @Override
    public void onItemClick(DataModel item) {
        if (item.getTileType() == DataModel.TileType.PLUS) {
            addTile(null, String.valueOf(UUID.randomUUID()));
        } else {
            Toast.makeText(getApplicationContext(), "You have selected the item with identifier " + item.getUUID(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addTile(String urlString, String uuid) {
        String url = String.format("http://placehold.it/360x360/8000ff/ffffff&text=Image -> %s", count);
        mAdapter.addItem(mDataModels.size() - 1, new DataModel(String.valueOf(count), uuid, urlString == null ? url : urlString, DataModel.TileType.REGULAR));
        count++;
    }

    private class DownloadJson extends AsyncTask<Void, Void, SnakeRecyclerJson> {
        private Gson gson;

        public DownloadJson() {
            gson = new Gson();
        }

        @Override
        protected SnakeRecyclerJson doInBackground(Void... voids) {
            // Network operation in background
            return gson.fromJson(loadJSONFromAsset(), SnakeRecyclerJson.class);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(SnakeRecyclerJson snakeRecyclerJson) {
            for (int i = 0; i < snakeRecyclerJson.getItems().size(); i++) {
                addTile(snakeRecyclerJson.getItems().get(i).getImageUrlString(), snakeRecyclerJson.getItems().get(i).getUuid());
            }
        }

        private String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getAssets().open("snake.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }
    }
}
