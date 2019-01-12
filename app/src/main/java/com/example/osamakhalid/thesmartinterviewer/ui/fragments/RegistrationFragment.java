package com.example.osamakhalid.thesmartinterviewer.ui.fragments;

import android.content.Context;
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
 * {@link RegistrationFragment.OnRegFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener, LoginRegisterContract.MainViewRegistration {

    private OnRegFragmentInteractionListener mListener;

    EditText username, password, name;
    Button regBtn;
    LoginRegisterContract.presenter presenter;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        name = view.findViewById(R.id.name);
        regBtn = view.findViewById(R.id.register);
        regBtn.setOnClickListener(this);
        presenter = new LoginRegistrationPresenter(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegFragmentInteractionListener) {
            mListener = (OnRegFragmentInteractionListener) context;
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
        if (view.getId() == regBtn.getId()) {
            presenter.registerUser(username.getText().toString(), password.getText().toString(),
                    name.getText().toString());
        }
    }

    @Override
    public void userAlreadyExists() {
        username.setError("User already exists");
    }

    @Override
    public void successfullyRegistered() {
        mListener.onRegFragmentInteraction();
        Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
        username.setText("");
        password.setText("");
        name.setText("");
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nameEmpty() {
        name.setError("Please enter your name");
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
        presenter.onRegDestroy();
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
    public interface OnRegFragmentInteractionListener {
        // TODO: Update argument type and name

        void onRegFragmentInteraction();
    }
}
