package com.example.basemedia.model;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 1/16/2018.
 */

public class BaseObject extends Object {


    public JSONObject jsonObject = new JSONObject();

    public BaseObject() {

    }

    public BaseObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public int getIntFromJsonObj(String key) {
        int result = 0;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getInt(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getJSONObjectInJSONArrayAtIndex(int index, JSONArray array){
        JSONObject result = null;
        try{
            result = array.getJSONObject(index);
        }
        catch (Exception e){
            e.printStackTrace();
            result = null;
        }
        finally {
            return result;
        }
    }

    public int getIntFromJsonObj(String key, int defaultValue) {
        int result = defaultValue;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getInt(key);
                }
            }
        } catch (Exception e) {
            result = defaultValue;
            e.printStackTrace();
        }
        return result;
    }

    public int getIntFromJsonObj(String key, JSONObject subObject) {
        int result = 0;
        try {
            if (subObject != null) {
                if (subObject.has(key) && !subObject.isNull(key)) {
                    result = subObject.getInt(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getDoubleFromJsonObj(String key) {
        double result = 0;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getDouble(key);
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    public double getDoubleFromJsonObj(String key, double defaultValue) {
        double result = defaultValue;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getDouble(key);
                }
            }
        } catch (Exception e) {
            result = defaultValue;
        }
        return result;
    }

    public String getStringFromJsonObj(String key) {
        String result = "";
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getString(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getStringFromJsonObj(String key, String defaultValue) {
        String result = defaultValue;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getString(key);
                }
            }
        } catch (Exception e) {
            result = defaultValue;
            e.printStackTrace();
        }
        return result;
    }

    public String getStringFromJsonObj(String key, JSONObject subObject) {
        String result = "";
        try {
            if (subObject != null) {
                if (subObject.has(key) && !subObject.isNull(key)) {
                    result = subObject.getString(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean getBooleanFromJsonObj(String key) {
        boolean result = false;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getBoolean(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean getBooleanFromJsonObj(String key, boolean defaultValue) {
        boolean result = defaultValue;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getBoolean(key);
                }
            }
        } catch (Exception e) {
            result = defaultValue;
            e.printStackTrace();
        }
        return result;
    }

    public boolean getBooleanFromJsonObj(String key, JSONObject subObject) {
        boolean result = false;
        try {
            if (subObject != null) {
                if (subObject.has(key) && !subObject.isNull(key)) {
                    result = subObject.getBoolean(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Long getLongFromJsonObj(String key) {
        long result = 0;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getLong(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Long getLongFromJsonObj(String key, long defaultValue) {
        long result = defaultValue;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key) && !this.jsonObject.isNull(key)) {
                    result = this.jsonObject.getLong(key);
                }
            }
        } catch (Exception e) {
            result = defaultValue;
            e.printStackTrace();
        }
        return result;
    }

    public Long getLongFromJsonObj(String key, JSONObject subObject) {
        long result = 0;
        try {
            if (subObject != null) {
                if (subObject.has(key) && !subObject.isNull(key)) {
                    result = subObject.getLong(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getJsonObjectFromJsonObj(String key) {
        JSONObject result = null;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key)) {
                    if (!this.jsonObject.isNull(key)) {
                        result = this.jsonObject.getJSONObject(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONArray getJsonArrayFromJsonObj(String key) {
        JSONArray result = null;
        try {
            if (this.jsonObject != null) {
                if (this.jsonObject.has(key)) {
                    if (!this.jsonObject.isNull(key)) {
                        result = this.jsonObject.getJSONArray(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JSONArray getJsonArrayFromJsonObj(String key, JSONObject objJSON) {
        JSONArray result = null;
        try {
            if (objJSON != null) {
                if (objJSON.has(key)) {
                    if (!objJSON.isNull(key)) {
                        result = objJSON.getJSONArray(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isExistDataByKey(String key) {
        boolean result = false;

        if (this.jsonObject == null)
            return false;

        try {
            if (this.jsonObject.has(key)) {
                if (!this.jsonObject.isNull(key)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JsonArray string2JsonArray(String input) {
        JsonArray result = null;
        try {
            JsonParser parser = new JsonParser();
            JsonElement tradeElement = parser.parse(input);
            result = tradeElement.getAsJsonArray();
        } catch (Exception e) {

        }
        return result;
    }
    public boolean isJSONOValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
