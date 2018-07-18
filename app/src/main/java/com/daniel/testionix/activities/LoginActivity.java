package com.daniel.testionix.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daniel.testionix.R;
import com.daniel.testionix.core.models.LoginObject;
import com.daniel.testionix.core.retrofit.ServiceUtils;
import com.daniel.testionix.core.retrofit.methods.ApiMethods;
import com.daniel.testionix.core.views.CustomProgressDialog;
import com.daniel.testionix.fragments.HomeFragment;
import com.daniel.testionix.utils.SystemUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ApiMethods mAPIService;
    private CustomProgressDialog progress;

    private EditText editRut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initVariables();

    }

    private void initVariables() {

        mAPIService = ServiceUtils.getAPIService();
        editRut = findViewById(R.id.editRut);
        RelativeLayout rlEntrar = findViewById(R.id.rlEntrar);

        rlEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rlEntrar:
                login();
                break;
        }
    }

    private void login() {

        String auxRut = editRut.getText().toString();

        if (!"".equalsIgnoreCase(auxRut))
            if (validateRut())
                cryptoRut(auxRut);
            else
                Toast.makeText(this, "Debe ingresar un RUT válido", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Debe ingresar un RUT", Toast.LENGTH_LONG).show();

    }

    private boolean validateRut() {
        return true;
    }

    @SuppressLint("GetInstance")
    private void cryptoRut(String auxRut) {

        String key = "ionix123456";

        try {

            DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            byte[] byteText = auxRut.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String cryptoRut = Base64.encodeToString(cipher.doFinal(byteText), Base64.DEFAULT);

            callService(cryptoRut.replaceAll("\n", ""));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al iniciar sesión. Intente nuevamente.", Toast.LENGTH_LONG).show();
        }

    }

    private void callService(String cryptoRut) {
        showProgress();

        mAPIService.getUser(cryptoRut).enqueue(new Callback<LoginObject>() {
            @Override
            public void onResponse(@NonNull Call<LoginObject> call, @NonNull Response<LoginObject> response) {

                dismissProgress();
                if (response.body() != null) {
                    assert response.body() != null;
                    if (response.body().getResult().getItems().size() > 0)
                        initHome(response.body());
                    else
                        Toast.makeText(LoginActivity.this, "Sin información", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<LoginObject> call, @NonNull Throwable t) {
                Log.v("LoginActivity", t.toString());
                dismissProgress();
                Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initHome(LoginObject loginObject) {

        initFragment(HomeFragment.newInstance(loginObject));

    }

    private void initFragment(Fragment fragment) {

        SystemUtils.replaceFragment(LoginActivity.this,
                R.id.container,
                "DetailFragment",
                true,
                null,
                fragment);

    }

    private void showProgress() {
        progress = new CustomProgressDialog(this);
        progress.show();
        progress.setCancelable(true);
        progress.setCanceledOnTouchOutside(false);
    }

    private void dismissProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
            progress.cancel();
        }
    }
}
