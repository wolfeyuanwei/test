package com.cdjiahotx.mobilephoneclient.util;

import org.apache.http.HttpEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtil {
	private static AsyncHttpClient client = new AsyncHttpClient();
	static{
		client.setTimeout(30000);			//set the time out 5s
	}
	
	/**
	 * 
	 * @param urlString - the URL to send the request to
	 * @param res
	 */
	public static void get(String urlString, AsyncHttpResponseHandler res){
		client.get(urlString, res);
	}
	
	/**
	 * @param urlString
	 * @param params
	 * @param res
	 */
	public static void get(String urlString, RequestParams  params, AsyncHttpResponseHandler res){
		client.get(urlString, params, res);
	}
	
	public static void get(String urlString, JsonHttpResponseHandler res){
		client.get(urlString,res);
	}
	
	public static void get(String urlString, BinaryHttpResponseHandler res){
		client.get(urlString,res);
	}
	
	public static void get(String urlString, RequestParams params, JsonHttpResponseHandler res){
		client.get(urlString, params, res);
	}
	
	public static AsyncHttpClient getClient(){
		return client;
	}
	
	/**
	 * @param urlString
	 * @param res
	 */
	public static void post(String urlString, AsyncHttpResponseHandler res){
		client.post(urlString, res);
	}
	
	/**
	 * @param urlString
	 * @param params
	 * @param res
	 */
	public static void post(String urlString, RequestParams params, AsyncHttpResponseHandler res){
		client.post(urlString, params, res);
	}
	
	public static void post(String urlString, JsonHttpResponseHandler res){
		client.post(urlString, res);
	}
	
	public static void post(String urlString, RequestParams params, JsonHttpResponseHandler res){
		client.post(urlString, params, res);
	}
	
	public static void post(Context context, String url, HttpEntity entity, String contentType, JsonHttpResponseHandler res){
		client.post(context, url, entity, contentType, res);
	}
	
	public static void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler res){
		client.post(context, url, entity, contentType, res);
	}
}
