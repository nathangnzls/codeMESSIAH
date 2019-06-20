package net.netne.cmessiah.codemessiah;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditProfileRequest extends StringRequest {
    private static final String EDIT_REQUEST_URL = "http://mysite.ph/phpfiles/editprofile.php";
    private Map<String, String> params;

    public EditProfileRequest(String username, String password, String firstname, String lastname, String loc, String contactnumber, String pic, Response.Listener<String> listener) {
        super(Request.Method.POST, EDIT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("userName", username);
        params.put("userPassword", password);
        params.put("userFName", firstname);
        params.put("userLName", lastname);
        params.put("userLocation", loc);
        params.put("userContactNumber", contactnumber);
        params.put("userImage", pic);
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

}
