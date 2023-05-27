package ru.mirea.efimovar.mireaproject.ui.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.efimovar.mireaproject.MainActivity;
import ru.mirea.efimovar.mireaproject.R;
import ru.mirea.efimovar.mireaproject.databinding.ActivityFirebaseBinding;

public class FirebaseActivity extends AppCompatActivity {
    private static final String TAG = FirebaseActivity.class.getSimpleName();
    private ActivityFirebaseBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            binding.statusTextView.setText(getString(R.string.emailpassword_status_fmt,

                    user.getEmail(), user.isEmailVerified()));

            binding.detailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            binding.emailPasswordButtons.setVisibility(View.GONE);
            binding.emailPasswordFields.setVisibility(View.VISIBLE);
            binding.editTextPassword.setVisibility(View.GONE);
            binding.signedInButtons.setVisibility(View.VISIBLE);
            binding.signOut.setVisibility(View.VISIBLE);

        } else {
            binding.statusTextView.setText(R.string.signed_out);
            binding.detailTextView.setText(null);

            binding.emailPasswordButtons.setVisibility(View.VISIBLE);
            binding.emailPasswordFields.setVisibility(View.VISIBLE);
            binding.signedInButtons.setVisibility(View.VISIBLE);
            binding.signOut.setVisibility(View.GONE);

            binding.editTextPassword.setVisibility(View.VISIBLE);
        }
    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        /*
        if (!validateForm()) {
            return;
        }

         */


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {


                            Log.w(TAG, "createUserWithEmail:failure",

                                    task.getException());
                            Toast.makeText(FirebaseActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            Intent intent = new Intent(FirebaseActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {


                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(FirebaseActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }


                        if (!task.isSuccessful()) {

                            binding.statusTextView.setText(R.string.auth_failed);
                        }



                    }
                });

    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }




    public void onClickCreateAccount(View view){
        createAccount(binding.emailPasswordFields.getText().toString(), binding.editTextPassword.getText().toString());
    }

    public void onClickSignIn(View view){
        signIn(binding.emailPasswordFields.getText().toString(), binding.editTextPassword.getText().toString());

        Log.d(TAG, binding.statusTextView.getText().toString());

    }

    public void onClickSignOut(View view){
        signOut();
    }




}
