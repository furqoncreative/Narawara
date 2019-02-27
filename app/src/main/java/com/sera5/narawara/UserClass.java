package com.sera5.narawara;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserClass {
    private FirebaseAuth fba;
    private Context c;

    UserClass(Context c) {
        this.fba = FirebaseAuth.getInstance();
        this.c = c;
    }

    public FirebaseUser getUser() {
        return fba.getCurrentUser();
    }

    public FirebaseAuth getAuth() {
        return fba;
    }

    Task<AuthResult> createUser(String email, String password) {
        return fba.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signIn(String email, String password) {
        return fba.signInWithEmailAndPassword(email,password);
    }

    public void sendResetPasswordEmail(String email) {
        fba.sendPasswordResetEmail(email);
    }

    public Task<Void> changePassword(String passwd) {
        if(getUser()!=null) {
            return getUser().updatePassword(passwd);
        } else {
            return null;
        }
    }

    public Task<Void> changeEmail(String email) {
        if(getUser()!=null) {
            return getUser().updateEmail(email);
        } else {
            return null;
        }
    }

    public Task<Void> deleteUser() {
        if(getUser()!=null) {
            return getUser().delete();
        } else {
            return null;
        }
    }

    public void signOut() {
        if(getUser()!=null) {
            fba.signOut();
        }
    }
}
