package com.example.dbs_livelah;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends Fragment {

    private GridView gridView;


    public AppFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app, container, false);

        gridView = view.findViewById(R.id.app_grid);

        renderUserPlugins(((paylahApplication) getActivity().getApplication()).getUID(), gridView);


        return view;
    }


    private void renderUserPlugins(String uid, final GridView gridView){
        String url = "https://fsjgwumse3.execute-api.ap-southeast-1.amazonaws.com/prod/user/" + uid + "/plugins";

        RequestQueue userPluginRequestQueue = Volley.newRequestQueue(getActivity());
        StringRequest userPluginRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("getUserPlugins", "Success: " + response);

                try {
                    Gson gson;
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    JSONArray jsonArray = new JSONArray(response);
                    final ArrayList<Plugin> pluginArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.v("getUserPlugins", "Check ArrayList: " + jsonArray.getString(i));

                        jsonArray.getJSONObject(i).remove("uid");
                        jsonArray.getJSONObject(i).remove("pid");
                        Plugin plugin = gson.fromJson(jsonArray.getString(i), Plugin.class);
                        pluginArrayList.add(plugin);
                    }

                    final Plugin[] plugins = pluginArrayList.toArray(new Plugin[pluginArrayList.size()]);

                    Log.v("getUserPlugins", Integer.toString(plugins.length));

                    for (int i = 0; i < plugins.length; i++) {
                        Log.v("getUserPlugins", String.valueOf(i));
                        Log.v("getUserPlugins", plugins[i].getName());
                    }

                    PluginAdapter pluginAdapter = new PluginAdapter(getActivity(), plugins);

                    gridView.setAdapter(pluginAdapter);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View view, int position, long id) {
                            Plugin plugin = plugins[position];

                            Intent intent = new Intent(getActivity(), WebFrameActivity.class);
                            intent.putExtra("web_url", plugin.getPluginURL());
                            startActivity(intent);
                        }
                    });


                } catch (JSONException e) {
                    Log.v("getUserPlugins", e.toString());
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("getUserPlugins", error.toString());
            }
        });

        userPluginRequestQueue.add(userPluginRequest);
    }
}
