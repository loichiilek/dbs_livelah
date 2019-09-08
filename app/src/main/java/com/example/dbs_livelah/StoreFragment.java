package com.example.dbs_livelah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_store, container, false);
        ListView listView = view.findViewById(R.id.store_listview);

        renderAllPlugins(listView);
        return view;
    }

    private void renderAllPlugins(final ListView listView){
        String url = "https://fsjgwumse3.execute-api.ap-southeast-1.amazonaws.com/prod/plugins";

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

                    // Initialize arraylist for plugins
                    final ArrayList<Plugin> pluginArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Plugin plugin = gson.fromJson(jsonArray.getString(i), Plugin.class);
                        pluginArrayList.add(plugin);
                    }

                    final Plugin[] plugins = pluginArrayList.toArray(new Plugin[pluginArrayList.size()]);
                    StorePluginAdapter storePluginAdapter = new StorePluginAdapter(getActivity(), plugins);
                    listView.setAdapter(storePluginAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View view, int position, long id) {
                            downloadPlugin(((paylahApplication) getActivity().getApplication()).getUID(), Integer.toString(position + 1));
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

    private void downloadPlugin (String uid, String pid) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String url = "https://fsjgwumse3.execute-api.ap-southeast-1.amazonaws.com/prod/user/3/plugins";
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("userid", uid);
            jsonBody.put("plugin", pid);

            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(getActivity(), "Downloading...", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("downloadPlugin", error.toString());

                }
            });
            requestQueue.add(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
