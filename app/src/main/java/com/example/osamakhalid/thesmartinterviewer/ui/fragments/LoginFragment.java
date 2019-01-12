package com.example.osamakhalid.thesmartinterviewer.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.calls.LoginRegisterContract;
import com.example.osamakhalid.thesmartinterviewer.presenters.LoginRegistrationPresenter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnLoginFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginRegisterContract.MainViewLogin {

    private OnLoginFragmentInteractionListener mListener;

    EditText username, password;
    Button login, reg;
    LoginRegisterContract.presenter presenter;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.login);
        reg = view.findViewById(R.id.register);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
        presenter = new LoginRegistrationPresenter(this, getContext());
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
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
        if (view.getId() == login.getId()) {
            presenter.loginUser(username.getText().toString(), password.getText().toString());
        } else if (view.getId() == reg.getId()) {
            mListener.onLoginFragmentInteraction(OnLoginFragmentInteractionListener.REG);
        }
    }

    @Override
    public void notMatched() {
        Toast.makeText(getContext(), "Email or Password does not match", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void successfullyLogin() {
        mListener.onLoginFragmentInteraction(OnLoginFragmentInteractionListener.LOGIN);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emailEmpty() {
        username.setError("Please enter username");
    }

    @Override
    public void passwordEmpty() {
        password.setError("Please enter password");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onLoginDestroy();
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
    public interface OnLoginFragmentInteractionListener {
        // TODO: Update argument type and name
        public static final int REG = 0;
        public static final int LOGIN = 1;

        void onLoginFragmentInteraction(int i);
    }
}
