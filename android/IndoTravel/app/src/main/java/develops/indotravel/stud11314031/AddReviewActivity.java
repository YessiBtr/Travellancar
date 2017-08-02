package develops.indotravel.stud11314031;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import develops.indotravel.stud11314031.model.Review;

public class AddReviewActivity extends AppCompatActivity {

    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mDatabaseReference;

    String keterangan;
    int id_provinsi=1;
    int id_kota=1;
    int id_tempat=1;
    String Nama= "Aditya Pranata";
    String username= "siregaraditya";
    String email="siregaraditya@gmail.com";
    String tanggal="16/05/2017";

    EditText userInput;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        userInput = (EditText) findViewById(R.id.userInput);
        ratingBar = (RatingBar) findViewById(R.id.userRating);

        initFirebase();

        Button buttonadd = (Button) findViewById(R.id.buttonAdd);
        Button buttoncancel = (Button) findViewById(R.id.buttonCancel);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createContact();
            }
        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFireBaseDatabase.getReference();
    }

    private void createContact() {
        Review review = new Review();
        review.setEmail(this.email);
        review.setKomentar(userInput.getText().toString());
        review.setNama(this.Nama);
        review.setRating(Integer.parseInt(ratingBar.getRating()+""));
        review.setTanggal(this.tanggal);
        mDatabaseReference.child("review").child(id_provinsi+"").child(id_kota+"").child(id_tempat+"").child(username).setValue(review);
    }

}
