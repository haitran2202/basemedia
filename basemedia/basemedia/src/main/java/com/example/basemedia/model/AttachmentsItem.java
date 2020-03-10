package com.example.basemedia.model;

import android.text.format.DateUtils;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class AttachmentsItem extends BaseObject {

	@SerializedName("id")
	private int id = 0;

	@SerializedName("type")
	private String type = "";

	@SerializedName("title")
	private String title= "";

	@SerializedName("url")
	private String url= "";
	@SerializedName("duration")
	private String duration= "";
	@SerializedName("filename")
	private String filename= "";
	@SerializedName("id_from")
	private String id_from= "";

	/*
	thời gian nhắn
	 */
	private String sent_time="";
	private boolean stateSeen = false;

	public AttachmentsItem(JSONObject jsonObject) {
		super(jsonObject);
		this.id = getIntFromJsonObj("id");
		this.type = getStringFromJsonObj("type");
		this.title = getStringFromJsonObj("title");
		this.url = getStringFromJsonObj("url");
		this.id_from = getStringFromJsonObj("id_from");
		this.filename = getStringFromJsonObj("filename");
		this.duration = getStringFromJsonObj("duration");
	}
	public AttachmentsItem(JSONObject jsonObject, String sent_time, boolean stateSeen) {
		super(jsonObject);
		this.id = getIntFromJsonObj("id");
		this.type = getStringFromJsonObj("type");
		this.title = getStringFromJsonObj("title");
		this.url = getStringFromJsonObj("url");
		this.sent_time = sent_time;
		this.stateSeen = stateSeen;
		this.id_from = getStringFromJsonObj("id_from");
		this.filename = getStringFromJsonObj("filename");
		this.duration = getStringFromJsonObj("duration");
	}

	public AttachmentsItem() {

	}
    public AttachmentsItem(String uri, String type){
		this.url = uri;
		this.type = type;
	}
	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getId_from() {
		return id_from;
	}
	
	public void setId_from(String id_from) {
		this.id_from = id_from;
	}
	
	@Override
 	public String toString(){
		return 
			"AttachmentsItem{" + 
			"id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
		public String displayDuration(){
		if(duration.isEmpty()){
			return "";
		}else {
			long timeInMillisec = Long.parseLong(duration );
			return DateUtils.formatElapsedTime(timeInMillisec);
		}
		}

	public String getSent_time() {
		return sent_time;
	}

	public void setSent_time(String sent_time) {
		this.sent_time = sent_time;
	}

	public boolean isStateSeen() {
		return stateSeen;
	}

	public void setStateSeen(boolean stateSeen) {
		this.stateSeen = stateSeen;
	}
}