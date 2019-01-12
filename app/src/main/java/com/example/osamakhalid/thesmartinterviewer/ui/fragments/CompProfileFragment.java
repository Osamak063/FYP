package com.example.osamakhalid.thesmartinterviewer.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.base_connection.RetrofitInstance;
import com.example.osamakhalid.thesmartinterviewer.connection_interface.ClientAPIs;
import com.example.osamakhalid.thesmartinterviewer.models.LoginRegistrationResponse;
import com.example.osamakhalid.thesmartinterviewer.models.User;
import com.example.osamakhalid.thesmartinterviewer.utils.SaveSharedPreference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompProfileFragment.OnProfFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CompProfileFragment extends Fragment implements View.OnClickListener {

    private OnProfFragmentInteractionListener mListener;

    static final int PICK_IMG = 100;
    static final int FILE_CHOOSER = 200;
    Uri imageUri, cvUri;
    CircleImageView img;
    Button select, submit;
    TextView fileName, uploadPhoto;
    EditText fname, lname, address, education;
    Bitmap bitmap;

    public CompProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comp_profile, container, false);
        img = view.findViewById(R.id.dp);
        select = view.findViewById(R.id.select_file);
        fileName = view.findViewById(R.id.file_name);
        uploadPhoto = view.findViewById(R.id.upload_text);
        submit = view.findViewById(R.id.submit);
        fname = view.findViewById(R.id.fname);
        lname = view.findViewById(R.id.lname);
        address = view.findViewById(R.id.address);
        education = view.findViewById(R.id.education);
        select.setOnClickListener(this);
        uploadPhoto.setOnClickListener(this);
        submit.setOnClickListener(this);
        return view;
    }

    public void showGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_IMG) {
                imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                img.setImageURI(imageUri);
            } else if (requestCode == FILE_CHOOSER) {
                cvUri = data.getData();
                fileName.setText(cvUri.getLastPathSegment());
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProfFragmentInteractionListener) {
            mListener = (OnProfFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == select.getId()) {
            showChooser();
        } else if (view.getId() == uploadPhoto.getId()) {
            showGallery();
        } else if (view.getId() == submit.getId()) {
            User user = SaveSharedPreference.getUserData(getContext());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imgbyte = byteArrayOutputStream.toByteArray();
            Base64.encodeToString(imgbyte, Base64.DEFAULT);
            user.setFname(fname.getText().toString());
            user.setLname(lname.getText().toString());
            user.setEducation(education.getText().toString());
            user.setAddress(address.getText().toString());
            user.setPicture(Base64.encodeToString(imgbyte, Base64.DEFAULT));
            user.setCv(cvUri.getLastPathSegment());
            saveUserDetails(user);
        }
    }

    public void saveUserDetails(User user) {
        ClientAPIs clientAPIs = RetrofitInstance.getRetrofitInstance().create(ClientAPIs.class);
        Call<LoginRegistrationResponse> call = clientAPIs.CompleteDetails(user);
        call.enqueue(new Callback<LoginRegistrationResponse>() {
            @Override
            public void onResponse(Call<LoginRegistrationResponse> call, Response<LoginRegistrationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginRegistrationResponse data = response.body();
                    SaveSharedPreference.saveUserDetails(getContext(), data.getData().get(0));
                    mListener.onProfFragmentInteraction();
                }
            }

            @Override
            public void onFailure(Call<LoginRegistrationResponse> call, Throwable t) {

            }
        });

    }

    public void showChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, FILE_CHOOSER);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnProfFragmentInteractionListener {
        // TODO: Update argument type and name
        void onProfFragmentInteraction();
    }
}
