package com.faijan.feedin;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class profile_fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView username, user_mobile, user_email, total_flames,total_donation;

    ImageView profile_image;
    SharedPreferences UserDataSharedPreferences;
    SharedPreferences.Editor UserDataSharedPreferencesEditor;

    FirebaseAuth auth;

    FirebaseFirestore db;


    private String mParam1;
    private String mParam2;

    public profile_fragment() {
    }


    public static profile_fragment newInstance(String param1 , String param2) {
        profile_fragment fragment = new profile_fragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1 , param1 );
        args.putString( ARG_PARAM2 , param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate( R.layout.fragment_profile_fragment , container , false );
        UserDataSharedPreferences = getActivity().getSharedPreferences("UserPreferences",MODE_PRIVATE);
        UserDataSharedPreferencesEditor = UserDataSharedPreferences.edit();

        String pref_email = UserDataSharedPreferences.getString( "email", "" );
        String pref_name = UserDataSharedPreferences.getString( "name", "" );
        String pref_uid = UserDataSharedPreferences.getString( "uid", "" );
        String pref_mobile = UserDataSharedPreferences.getString( "mobile", "" );
        String pref_totalFlames = UserDataSharedPreferences.getString( "totalFlames", "" );
        String pref_totalDonation = UserDataSharedPreferences.getString( "totalDonation", "" );


        username = rootView.findViewById( R.id.username );
        user_mobile = rootView.findViewById( R.id.mobile );
        user_email = rootView.findViewById( R.id.email );
        total_flames = rootView.findViewById( R.id.total_flames );
        total_donation = rootView.findViewById( R.id.total_donation );

        profile_image = rootView.findViewById(R.id.profile_image);


        db = FirebaseFirestore.getInstance();
        auth =FirebaseAuth.getInstance();

        String uid = auth.getCurrentUser().getUid().toString();


        if((pref_name.isEmpty() && pref_email.isEmpty()) && (pref_email.equals("") && pref_name.equals(""))){

        }else {
            username.setText( pref_name );
            user_email.setText( pref_email );
            total_flames.setText( pref_totalFlames );
            total_donation.setText( pref_totalDonation );
            user_mobile.setText( pref_mobile );

        }




        if (uid.equals( pref_uid )){

            db.collection( "Users" )
                    .document(uid)
                    .get()
                    .addOnSuccessListener( new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot snapshot) {

                            String userName =  snapshot.getString( "name" );
                            String mobile =  snapshot.getString( "mobile" );
                            String email =  snapshot.getString( "email" );
                            String totalDonation =  snapshot.get( "totalDonation" ).toString();
                            String totalFlames =  snapshot.get( "totalFlames" ).toString();

                            username.setText( userName );
                            String mobile_no;
                            if (mobile.contentEquals( "+91" ) || mobile.length()>= 11){
                                mobile_no = mobile.substring(mobile.length() - 10);
                                user_mobile.setText( mobile_no );
                            }else {
                                mobile_no ="+91-"+mobile;
                                user_mobile.setText( mobile_no );
                            }
                            user_email.setText( email );
                            total_flames.setText( totalFlames );
                            total_donation.setText( totalDonation );

                            UserDataSharedPreferencesEditor.putString("totalFlames", totalFlames);
                            UserDataSharedPreferencesEditor.putString("totalDonation",totalDonation);
                            UserDataSharedPreferencesEditor.putString("mobile",mobile_no);
                            UserDataSharedPreferencesEditor.commit();
                            UserDataSharedPreferencesEditor.apply();
                        }
                    } )
                    .addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e( "Err", "f1" );

                            Toast.makeText( getContext(), "Error Occured... Please Restart the application", Toast.LENGTH_LONG ).show();
                        }
                    } );
        }else {
            Toast.makeText( getContext(), "Error Occured... Try login again", Toast.LENGTH_LONG ).show();
        }
        return rootView;
    }
}