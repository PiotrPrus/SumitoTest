package com.example.piotr.sumitotest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Piotr on 28.11.2016.
 */

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder>{



    private List<Entry> mEmployees;
    private Context mContext;

    public EmployeesAdapter(Context context, List<Entry> employees) {
        mEmployees = employees;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        parseEmployeesData();
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View employeesView = inflater.inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(employeesView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(EmployeesAdapter.ViewHolder viewHolder, int position) {
        Entry entry = mEmployees.get(position);

        TextView textView = viewHolder.listTextView;
        textView.setText(entry.getEmployeeId());
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView listTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            listTextView = (TextView) itemView.findViewById(R.id.list_text_view);
        }
    }

    private static final String EMPLOYEES_URL = "http://officewise.sumito.uk:8081/admin/employees?projectId=6";

    final OkHttpClient client = new OkHttpClient();

    ArrayList<Entry> employeesArrayList = new ArrayList<>();

    public static List<Entry> getEntries(String jsonData) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonData);
        final List<Entry> data = new ArrayList<>();
        for (int n=0; n < jsonArray.length(); n++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(n);
            final JSONArray tagsArray = jsonObject.getJSONArray("tags");
            final JSONObject uuidObject = tagsArray.getJSONObject(n);

            final Entry entry = new Entry(jsonObject.getInt("id"), uuidObject.getString("uuid"));
            data.add(entry);
        }
        return data;
    }

    public void parseEmployeesData() {
        final Request request = new Request.Builder()
                .url(EMPLOYEES_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("CALLBACK", "Request " + request + " failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    if (response.isSuccessful()) {
                        final String jsonData = response.body().string();
                        employeesArrayList.clear();
                        employeesArrayList.addAll(getEntries(jsonData));
                    } else {
                        //Dummy
                    }
                }catch (IOException | JSONException e) {
                    Log.e("Response", "Response" + response + "failed");
                }
            }
        });
    }

}
