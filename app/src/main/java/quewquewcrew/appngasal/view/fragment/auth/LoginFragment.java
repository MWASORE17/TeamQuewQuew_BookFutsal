package quewquewcrew.appngasal.view.fragment.auth;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.Lapangan;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;
import quewquewcrew.appngasal.view.activity.AuthActivity;
import quewquewcrew.appngasal.view.activity.MainActivity;
import quewquewcrew.appngasal.view.activity.ParentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    /* view component */
    private TextView register;
    private EditText email, password;
    private Button login;
    private TextInputLayout emailcontainer, passwordcontainer;

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_login, container, false);

        init(_view);
        event();

        return _view;
    }

    private void init(View view) {
        register = (TextView) view.findViewById(R.id.login_register);
        email = (EditText) view.findViewById(R.id.login_email);
        password = (EditText) view.findViewById(R.id.login_password);
        login = (Button) view.findViewById(R.id.login_login);
        emailcontainer = (TextInputLayout) view.findViewById(R.id.login_email_container);
        passwordcontainer = (TextInputLayout) view.findViewById(R.id.login_password_container);
    }

    private void event() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthActivity) getActivity()).changefragment(new RegisterFragment());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean _isvalid = true;
                emailcontainer.setErrorEnabled(false);
                passwordcontainer.setErrorEnabled(false);
                User user = new User();
                boolean _isemailexist = false;
                for (int i=0;i<User.users.size();i++) {
                    if (User.users.get(i).getEmail().equals(email.getText().toString())) {
                        _isemailexist = true;
                        user = User.users.get(i);
                        break;
                    }
                }
                if (TextUtils.isEmpty(email.getText())) {
                    _isvalid = false;
                    emailcontainer.setErrorEnabled(true);
                    emailcontainer.setError("Email is required");
                } else if (!AuthActivity.isemailvalid(email.getText().toString())) {
                    _isvalid = false;
                    emailcontainer.setErrorEnabled(true);
                    emailcontainer.setError("Email is not valid");
                } else if (!_isemailexist) {
                    emailcontainer.setErrorEnabled(true);
                    emailcontainer.setError("Email is not registered.");
                    _isvalid = false;
                }
                else if (TextUtils.isEmpty(password.getText())) {

                    passwordcontainer.setErrorEnabled(true);
                    passwordcontainer.setError("Password is required");
                    _isvalid = false;
                }
                else if (!user.getPassword().equals(password.getText().toString())) {
                    passwordcontainer.setErrorEnabled(true);
                    passwordcontainer.setError("Password is incorrect.");
                    _isvalid = false;
                }

                if (_isvalid) {
                    SessionManager sessionManager = SessionManager.with(getContext());
                    sessionManager.createsession(user);
                    ParentActivity.doChangeActivity(getContext(), MainActivity.class);
                }

            }
        });
    }

}
